package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.domain.Funding;
import com.kosmo.funfunhaejwo.jpa.domain.Member;
import com.kosmo.funfunhaejwo.jpa.domain.Order;
import com.kosmo.funfunhaejwo.jpa.domain.ProductImg;
import com.kosmo.funfunhaejwo.jpa.domain.semi.OrderStatus;
import com.kosmo.funfunhaejwo.jpa.fileset.FilePath;
import com.kosmo.funfunhaejwo.jpa.repository.FundingRepo;
import com.kosmo.funfunhaejwo.jpa.repository.MemberRepo;
import com.kosmo.funfunhaejwo.jpa.repository.OrderRepo;
import com.kosmo.funfunhaejwo.jpa.vo.FPay_fundingInfoVo;
import com.kosmo.funfunhaejwo.jpa.vo.FPay_setPayInfoVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FundingPayServiceImpl implements FundingPayService{
    private final FundingRepo fundingRepo;
    private final OrderRepo orderRepo;
    private final MemberRepo memberRepo;

    @Override
    public FPay_fundingInfoVo getFuningS(long fundingId, long memberId) {
        FPay_fundingInfoVo fundinginfo = new FPay_fundingInfoVo();
        Funding funding = fundingRepo.getById(fundingId);
        Member member = memberRepo.getById(memberId);
        List<ProductImg> piList = funding.getProduct().getProductImgs();
        for(ProductImg item: piList) {
            String fsrc = item.getFile_info().getFile_src();
            if (fsrc.contains("sub1"))
                fundinginfo.setPsImg(FilePath.BASIC_FILE_PATH + fsrc);
        }
        fundinginfo.setFTitle(funding.getFunding_title());
        fundinginfo.setFcMoney(funding.getFunding_collected_money());
        fundinginfo.setFtMoney(funding.getFunding_target_money());
        fundinginfo.setBtel(member.getPhone_number());

        return fundinginfo;
    }

    @Override
    public FPay_setPayInfoVo setPayS(long fundingId, long memberId) {
        FPay_setPayInfoVo info = new FPay_setPayInfoVo();
        Funding funding = fundingRepo.getById(fundingId);
        Member member = memberRepo.getById(memberId);
        LocalDateTime nt = LocalDateTime.now();
        Instant instant = nt.atZone(ZoneId.systemDefault()).toInstant();
        java.util.Date date = java.util.Date.from(instant);
        String merchant_uid = Long.toString(memberId) + "_merchant_" + date.getTime();
        Order order = Order.builder().member(member).funding(funding).product(funding.getProduct()).orderDate(nt).merchantUid(merchant_uid).orderStatus(OrderStatus.PROCEEDING).build();
        orderRepo.save(order);

        order = orderRepo.findByMerchantUid(merchant_uid);
        info.setOrderId(order.getId());
        info.setMerchant_uid(merchant_uid);

        return info;
    }

    @Override
    @Transactional
    public boolean updatePayS(long fundingId, long orderId, long amount, String imp_uid, String pg_id) {
        try{
            Order curOrder = orderRepo.getById(orderId);
            Order order = Order.builder().id(orderId).member(curOrder.getMember()).product(curOrder.getProduct()).funding(curOrder.getFunding())
                    .total_payment(amount).orderDate(curOrder.getOrderDate()).merchantUid(curOrder.getMerchantUid())
                    .i_port_id(imp_uid).pg_id(pg_id)
                    .orderStatus(OrderStatus.SUCCESS).build();
            orderRepo.save(order);
            Funding curFunding = fundingRepo.getById(fundingId);
            Funding updFunding = Funding.builder().
                    id(fundingId).product(curFunding.getProduct()).member(curFunding.getMember())
                    .funding_title(curFunding.getFunding_title())
                    .funding_expired_time(curFunding.getFunding_expired_time()).funding_create_time(curFunding.getFunding_create_time())
                    .funding_people_count(curFunding.getFunding_people_count()+1)
                    .funding_collected_money(curFunding.getFunding_collected_money()+amount)
                    .funding_target_money(curFunding.getFunding_target_money())
                    .funding_type(curFunding.getFunding_type())
                    .build();
            fundingRepo.save(updFunding);
        }catch(DataAccessException dae){
            log.info("#FundingPayServiceImpl updatePayS: " + dae);
            return false;
        }
        return true;
    }

    @Override
    public void updateFailS(long orderId) {
        Order curOrder = orderRepo.getById(orderId);
        Order order = Order.builder().id(orderId).member(curOrder.getMember()).product(curOrder.getProduct()).funding(curOrder.getFunding())
                .orderDate(curOrder.getOrderDate()).merchantUid(curOrder.getMerchantUid())
                .orderStatus(OrderStatus.FAIL).build();
        try{
            orderRepo.save(order);
        }catch(DataAccessException dae){
            log.info("#FundingPayServiceImpl updateFailS: " + dae);
        }
    }
}
