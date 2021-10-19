package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.*;
import com.kosmo.funfunhaejwo.jpa.domain.semi.FundingType;
import com.kosmo.funfunhaejwo.jpa.domain.semi.OrderStatus;
import com.kosmo.funfunhaejwo.jpa.fileset.FilePath;
import com.kosmo.funfunhaejwo.jpa.repository.FundingRepo;
import com.kosmo.funfunhaejwo.jpa.repository.MemberRepo;
import com.kosmo.funfunhaejwo.jpa.repository.OrderRepo;
import com.kosmo.funfunhaejwo.jpa.repository.ProductRepo;
import com.kosmo.funfunhaejwo.jpa.vo.BPay_productInfoVo;
import com.kosmo.funfunhaejwo.jpa.vo.BPay_setPayInfoVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuyPayServiceImpl implements BuyPayService{
    private final ProductRepo productRepo;
    private final FundingRepo fundingRepo;
    private final OrderRepo orderRepo;
    private final MemberRepo memberRepo;

    @Override
    public BPay_productInfoVo getProductS(long productId, long memberId) {
        BPay_productInfoVo productInfo = new BPay_productInfoVo();
        Product product = productRepo.getById(productId);
        Member member = memberRepo.getById(memberId);
        List<ProductImg> pilist = product.getProductImgs();
        for(ProductImg item : pilist){
            String fsrc = item.getFile_info().getFile_src();
            if(fsrc.contains("sub1")){
                productInfo.setPsImg(FilePath.BASIC_FILE_PATH + fsrc);
                break;
            }
        }
        productInfo.setPTitle(product.getProduct_name());
        productInfo.setPprice(product.getProduct_price());
        productInfo.setBtel(member.getPhone_number());

        return productInfo;
    }

    @Override
    public BPay_setPayInfoVo setPayS(long productId, long memberId) {
        BPay_setPayInfoVo info = new BPay_setPayInfoVo();
        Product product = productRepo.getById(productId);
        Member member = memberRepo.getById(memberId);
        LocalDateTime nt = LocalDateTime.now();
        Instant instant = nt.atZone(ZoneId.systemDefault()).toInstant();
        java.util.Date date = java.util.Date.from(instant);
        String merchant_uid = Long.toString(memberId) + "_merchant_" + date.getTime();
        Order order = Order.builder().member(member).product(product).orderDate(nt).merchantUid(merchant_uid).orderStatus(OrderStatus.PROCEEDING).build();
        orderRepo.save(order);

        order = orderRepo.findByMerchantUid(merchant_uid);
        info.setOrderId(order.getId());
        info.setMerchant_uid(merchant_uid);

        return info;
    }

    @Transactional
    @Override
    public boolean updatePayS(long productId, long orderId, long amount, String imp_uid, String pg_id) {
        Product product = productRepo.getById(productId);
        Order curOrder = orderRepo.getById(orderId);
        
        //상품가격에 맞게 결제를 했는지 검증 + 주문 상품 아이디가 다른지 조회
        if(amount != product.getProduct_price() || product.getId() != curOrder.getProduct().getId()){   //다른 경우 결제 실패로 처리
            Order order = Order.builder().id(orderId).member(curOrder.getMember()).product(curOrder.getProduct())
                    .total_payment(amount)
                    .orderDate(curOrder.getOrderDate()).merchantUid(curOrder.getMerchantUid())
                    .i_port_id(imp_uid).pg_id(pg_id)
                    .orderStatus(OrderStatus.FAIL).build();
            try{
                orderRepo.save(order);
            }catch(DataAccessException dae){
                log.info("#FundingPayServiceImpl updateFailS: " + dae);
            }
            return false;
        }

        try{
            LocalDateTime curTime = LocalDateTime.now();
            Funding saveFunding = Funding.builder().product(product).member(curOrder.getMember())
                    .funding_title(product.getProduct_name())
                    .funding_expired_time(curTime).funding_create_time(curTime)
                    .funding_people_count(1).funding_collected_money(amount).funding_target_money(amount)
                    .funding_type(FundingType.BUY).build();
            saveFunding = fundingRepo.save(saveFunding);
            //product funding수 +1 추가할 것

            Order order = Order.builder().id(orderId).member(curOrder.getMember()).product(curOrder.getProduct()).funding(saveFunding)
                    .total_payment(amount).orderDate(curOrder.getOrderDate()).merchantUid(curOrder.getMerchantUid())
                    .i_port_id(imp_uid).pg_id(pg_id)
                    .orderStatus(OrderStatus.SUCCESS).build();
            orderRepo.save(order);
        }catch(DataAccessException dae){
            log.info("#FundingPayServiceImpl updatePayS: " + dae);
            return false;
        }
        return true;
    }

    @Override
    public void updateFailS(long orderId) {
        Order curOrder = orderRepo.getById(orderId);
        Order order = Order.builder().id(orderId).member(curOrder.getMember()).product(curOrder.getProduct())
                .orderDate(curOrder.getOrderDate()).merchantUid(curOrder.getMerchantUid())
                .orderStatus(OrderStatus.FAIL).build();
        try{
            orderRepo.save(order);
        }catch(DataAccessException dae){
            log.info("#FundingPayServiceImpl updateFailS: " + dae);
        }
    }

}
