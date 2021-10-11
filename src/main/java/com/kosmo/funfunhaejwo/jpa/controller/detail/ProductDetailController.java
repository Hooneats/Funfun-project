package com.kosmo.funfunhaejwo.jpa.controller.detail;

import com.kosmo.funfunhaejwo.jpa.domain.*;
import com.kosmo.funfunhaejwo.jpa.domain.semi.ImgCode;
import com.kosmo.funfunhaejwo.jpa.repository.ProductRepo;
import com.kosmo.funfunhaejwo.jpa.service.LikeService;
import com.kosmo.funfunhaejwo.jpa.service.MemberService;
import com.kosmo.funfunhaejwo.jpa.service.ProductDetailService;
import com.kosmo.funfunhaejwo.jpa.service.ProductImgService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("product")
class ProductDetailController {
    private final ProductDetailService productDetailService;
    private final ProductImgService productImgService;
    private final LikeService likeService;
    private final MemberService memberService;

    @PostMapping("productDetail")
    public ResponseEntity<ReturnProductDetailInfo> getProductDetail(@RequestParam(required = false) Long product_id,
                                                                    HttpServletRequest request) throws IOException {
//        log.info(String.valueOf(product_id));
        Product getProduct = new Product();
        ProductImg getProductImg = new ProductImg();
        Like like = new Like();
        List<Like> likeList = new ArrayList<>();
        List<ProductImg> productImgList = new ArrayList<>();

        getProduct = productDetailService.getProductById(product_id);
        log.info("product is : {}", getProduct);

        productImgList = productImgService.getProductImgByProduct(getProduct);
        List<String> productImgStringList = productImgList.stream().map(productImg -> {
            String imgUrl="http://127.0.0.1:8887";
            String imgUrlDetail=productImg.getFile_info().getFile_src();
            return imgUrl+imgUrlDetail;

//            return productImg.getFile_info().getFile_src();
        }).collect(Collectors.toList());

        likeList = likeService.getByProduct(getProduct);
        List<String> productLikeList = likeList.stream().map(likes ->{
            return likes.getMember().getEmail();

        }).collect(Collectors.toList());

        ReturnProductDetailInfo returnProductInfo = ReturnProductDetailInfo.builder()
                .product_id(getProduct.getId())
                .product_name(getProduct.getProduct_name())
                .product_price(getProduct.getProduct_price())
                .product_like_count(getProduct.getProduct_like_count())
                .funding_count(getProduct.getFunding_count())
                .product_brand(getProduct.getProduct_brand())
                .productImg(productImgStringList)
                .product_categoryId(getProduct.getCategory().getId())
                .product_like_list(productLikeList)
                .build();
        log.info(returnProductInfo.toString());
        return ResponseEntity.ok().body(returnProductInfo);

    }
    @PostMapping("/like/update")
    public ResponseEntity<ReturnProductDetailInfo> okLikeUpdate(@RequestParam("like_up") Boolean  likeUp,
                                                                @RequestParam("member_id") Long  member_id,
                                                                @RequestParam("product_id") Long product_id) {
        log.info("# LikeCount from front1 : {}", likeUp);
        log.info("# LikeCount from front2: {}", product_id);

        productDetailService.likeCountUp(product_id,member_id,likeUp);
        if(likeUp == false){

        }

        return new ResponseEntity<> (HttpStatus.OK);
    }

}
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
class ReturnProductDetailInfo{
    private Long product_id;
    private String product_name;
    private Long product_price;
    private int funding_count;
    private int product_like_count;
    private String product_brand;
    private List<String> productImg;
    private Long product_categoryId;
    private List<String> product_like_list;

}
