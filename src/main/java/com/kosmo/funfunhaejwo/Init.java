package com.kosmo.funfunhaejwo;


import com.kosmo.funfunhaejwo.jpa.domain.*;
import com.kosmo.funfunhaejwo.jpa.domain.semi.*;
import com.kosmo.funfunhaejwo.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
@RequiredArgsConstructor
public class Init {

    private final MemberRepo memberRepo;
    private final ProfileImgRepo profileImgRepo;
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    private final ProductImgRepo productImgRepo;
    private final LikeRepo likeRepo;
    private final OrderRepo orderRepo;

    private final PasswordEncoder passwordEncoder;

    private final FundingRepo fundingRepo;
    List<Member> memberList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();

    @PostConstruct
    void insertInitDB() {
//        modifyPassword();

//        orderRepo.deleteAll();
//        likeRepo.deleteAll();
//        profileImgRepo.deleteAll();
//        memberRepo.deleteAll();
//        productImgRepo.deleteAll();
//        productRepo.deleteAll();
//        categoryRepo.deleteAll();
        likeRepo.deleteAll();
//        profileImgRepo.deleteAll();
//        memberRepo.deleteAll();
//        productImgRepo.deleteAll();
//        productRepo.deleteAll();
//        categoryRepo.deleteAll();
//        fundingRepo.deleteAll();
//
//
//


//        insertMember();
        madeProductLike();
        madeFunding();
        madeOrders();
//        completedFunding();

    }

    void modifyPassword() {
        List<Member> allMember = memberRepo.findAll();
        allMember.forEach(res -> {
            System.out.println("res = " + res.getPassword());
            if (res.getPassword().equals("1234")) {
                res.setPasswordEncoded(passwordEncoder.encode(res.getPassword()));
            }
            memberRepo.saveAll(allMember);
        });
        if (memberRepo.findByEmail("hooneats@naver.com").orElse(null) == null) {
            Member hoo = Member.builder()
                    .role(Role.ADMIN)
                    .login_api(LoginApi.EMAIL)
                    .email("hooneats@naver.com")
                    .password(passwordEncoder.encode("123456789asd!"))
                    .nic_name("후니츠")
                    .phone_number("01077712027")
                    .build();
            memberRepo.save(hoo);
        }
    }
    void madeFunding() {
        Member member1 = memberRepo.findById(1L).orElse(null);
        Member member2 = memberRepo.findById(2L).orElse(null);
        Member member3 = memberRepo.findById(3L).orElse(null);
        Product product1 = productRepo.findById(1L).orElse(null);
        Product product2 = productRepo.findById(2L).orElse(null);
        Product product3 = productRepo.findById(3L).orElse(null);
        Funding funding1 = Funding.builder()
                .id(1L)
                .member(member1)
                .product(product1)
                .funding_title("난 돈도좋아")
                .funding_type(FundingType.FUNDING)
                .funding_people_count(0)
                .funding_target_money(30000L)
                .funding_collected_money(10000L)
                .funding_create_time(LocalDateTime.now())
                .funding_expired_time(LocalDateTime.of(2021, 10, 27, 00, 00))
                .build();
        Funding funding2 = Funding.builder()
                .id(2L)
                .member(member2)
                .product(product2)
                .funding_title("천원씩만 내주라")
                .funding_type(FundingType.FUNDING)
                .funding_people_count(0)
                .funding_target_money(20000L)
                .funding_collected_money(18000L)
                .funding_create_time(LocalDateTime.now())
                .funding_expired_time(LocalDateTime.of(2021, 10, 26, 00, 00))
                .build();
        Funding funding3 = Funding.builder()
                .id(3L)
                .member(member3)
                .product(product3)
                .funding_title("요즘 목이안좋아서")
                .funding_type(FundingType.FUNDING)
                .funding_people_count(0)
                .funding_target_money(50000L)
                .funding_collected_money(30000L)
                .funding_create_time(LocalDateTime.now())
                .funding_expired_time(LocalDateTime.of(2021, 10, 25, 00, 00))
                .build();
        fundingRepo.save(funding1);
        fundingRepo.save(funding2);
        fundingRepo.save(funding3);
        Member hoo = memberRepo.findByEmail("hooneats@naver.com").orElse(null);
        Funding funding4 = Funding.builder()
                .id(4L)
                .member(hoo)
                .product(product3)
                .funding_title("가즈아~!")
                .funding_type(FundingType.FUNDING)
                .funding_people_count(0)
                .funding_target_money(50000L)
                .funding_collected_money(15000L)
                .funding_create_time(LocalDateTime.now())
                .funding_expired_time(LocalDateTime.of(2021, 11, 18, 00, 00))
                .build();
        Funding funding5 = Funding.builder()
                .id(5L)
                .member(hoo)
                .product(product2)
                .funding_title("선물줘")
                .funding_type(FundingType.FUNDING)
                .funding_people_count(0)
                .funding_target_money(25000L)
                .funding_collected_money(3000L)
                .funding_create_time(LocalDateTime.now())
                .funding_expired_time(LocalDateTime.of(2021, 11, 20, 00, 00))
                .build();
        Funding funding6 = Funding.builder()
                .id(6L)
                .member(hoo)
                .product(product1)
                .funding_title("이거하나만")
                .funding_type(FundingType.FUNDING)
                .funding_people_count(0)
                .funding_target_money(30000L)
                .funding_collected_money(10000L)
                .funding_create_time(LocalDateTime.now())
                .funding_expired_time(LocalDateTime.of(2021, 11, 19, 00, 00))
                .build();
        fundingRepo.save(funding4);
        fundingRepo.save(funding5);
        fundingRepo.save(funding6);
    }
    void madeOrders() {
        Member hoo = memberRepo.findByEmail("hooneats@naver.com").orElse(null);
        Product product1 = productRepo.findById(1L).orElse(null);
        Product product2 = productRepo.findById(2L).orElse(null);
        Product product3 = productRepo.findById(3L).orElse(null);
        Funding funding1 = fundingRepo.findById(1L).orElse(null);
        Funding funding5 = fundingRepo.findById(5L).orElse(null);
        Funding funding6 = fundingRepo.findById(6L).orElse(null);

        Order build1 = Order.builder()
                .id(4L)
                .member(hoo)
                .funding(funding1)
                .product(product1)
                .total_payment(50000L)
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.SUCCESS)
                .build();
        Order build2 = Order.builder()
                .id(5L)
                .member(hoo)
                .funding(funding6)
                .product(product1)
                .total_payment(50000L)
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.SUCCESS)
                .build();
        Order build3 = Order.builder()
                .id(6L)
                .member(hoo)
                .funding(funding5)
                .product(product2)
                .total_payment(50000L)
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.SUCCESS)
                .build();
        orderRepo.save(build1);
        orderRepo.save(build2);
        orderRepo.save(build3);
    }
    void madeProductLike() {
        List<Product> productList = new ArrayList<>();
        List<Like> likeList = new ArrayList<>();
        Member hooneats = memberRepo.findById(25L).orElse(null);
        for (long i = 1L; i < 11L; i++) {
            Product product = productRepo.findById(i).orElse(null);
            product.likeControl(true);
            Like buildLike = Like.builder()
                    .product(product)
                    .member(hooneats)
                    .build();
            System.out.println("product_like_count = " + product.getProduct_like_count());
            likeList.add(buildLike);
            productList.add(product);
        }
        likeRepo.saveAll(likeList);
        productRepo.saveAll(productList);
        System.out.println("likeList = " + likeList);
        System.out.println("productList = " + productList);
    }

    // 펀딩 완료된 펀딩은 버튼이 보일것이다.
    void completedFunding() {
        Product product = productRepo.findById(1L).orElse(null);
        Member member = memberRepo.findById(25L).orElse(null);
        Funding buildFunding1 = Funding.builder()
                .funding_expired_time(LocalDateTime.of(2021, 10, 17, 00, 00))
                .funding_target_money(50000L)
                .funding_title("여러분 완료되었어요1!!!")
                .funding_create_time(LocalDateTime.of(2021, 10, 5, 00, 00))
                .funding_people_count(50)
                .funding_collected_money(45000L)
                .funding_type(FundingType.FUNDING)
                .product(product)
                .member(member)
                .build();
        Funding buildFunding2 = Funding.builder()
                .funding_expired_time(LocalDateTime.of(2021, 10, 17, 00, 00))
                .funding_target_money(50000L)
                .funding_title("여러분 완료되었어요2!!!")
                .funding_create_time(LocalDateTime.of(2021, 10, 5, 00, 00))
                .funding_people_count(50)
                .funding_collected_money(50000L)
                .funding_type(FundingType.FUNDING)
                .product(product)
                .member(member)
                .build();
        Funding buildFunding3 = Funding.builder()
                .funding_expired_time(LocalDateTime.of(2021, 10, 17, 00, 00))
                .funding_target_money(50000L)
                .funding_title("여러분 완료되었어요3!!!")
                .funding_create_time(LocalDateTime.of(2021, 10, 5, 00, 00))
                .funding_people_count(50)
                .funding_collected_money(80000L)
                .funding_type(FundingType.FUNDING)
                .product(product)
                .member(member)
                .build();
        fundingRepo.save(buildFunding1);
        fundingRepo.save(buildFunding2);
        fundingRepo.save(buildFunding3);
    }

    void insertMember() {
//        List<Member> memberList = new ArrayList<>();
        List<ProfileImg> profileImgList = new ArrayList<>();

        ProfileImg build1 = ProfileImg.builder().file_info(new File_info("profile/profile.jpg", "profile.jpg")).build();
        Member member1 = Member.builder().email("hooneats@naver.com").password(passwordEncoder.encode("123456789asd!")).nic_name("후니츠").login_api(LoginApi.EMAIL).role(Role.ADMIN).phone_number("01077712027").build();

        profileImgList.add(build1);
        profileImgRepo.saveAll(profileImgList);

        memberList.add(member1);
        memberRepo.saveAll(memberList);
    }
//    void insertProduct() {
//        List<ProductImg> productImgList = new ArrayList<>();
//        List<Category> categoryList = new ArrayList<>();
////        List<Product> productList = new ArrayList<>();
//
//        Category category1 = new Category(1L, "김규민프렌즈");
//        Category category2 = new Category(2L, "김동원프렌즈");
//        Category category3 = new Category(3L, "신대현프렌즈");
//        Category category4 = new Category(4L, "신혜림프렌즈");
//        Category category5 = new Category(5L, "이지연프렌즈");
//
//        Product product1 = Product.builder().id(1L).category(category1).product_brand("모두의프렌즈").funding_count(610).product_like_count(10000).product_name("테스트가").product_price(38000L).product_stock(182).build();
//        Product product2 = Product.builder().id(2L).category(category2).product_brand("모두의프렌즈").funding_count(520).product_like_count(8000).product_name("테스트나").product_price(56000L).product_stock(182).build();
//        Product product3 = Product.builder().id(3L).category(category3).product_brand("모두의프렌즈").funding_count(430).product_like_count(50).product_name("테스트다").product_price(1623000L).product_stock(182).build();
//        Product product4 = Product.builder().id(4L).category(category4).product_brand("모두의프렌즈").funding_count(340).product_like_count(900).product_name("테스트라").product_price(1450000L).product_stock(182).build();
//        Product product5 = Product.builder().id(5L).category(category5).product_brand("모두의프렌즈").funding_count(250).product_like_count(14000).product_name("테스트마").product_price(63000L).product_stock(182).build();
//        Product product6 = Product.builder().id(6L).category(category1).product_brand("모두의프렌즈").funding_count(160).product_like_count(16200).product_name("테스트바").product_price(73000L).product_stock(182).build();
//        Product product7 = Product.builder().id(7L).category(category1).product_brand("모두의프렌즈").funding_count(70).product_like_count(17200).product_name("테스트사").product_price(83000L).product_stock(182).build();
//        Product product8 = Product.builder().id(8L).category(category2).product_brand("모두의프렌즈").funding_count(80).product_like_count(18200).product_name("테스트아").product_price(93000L).product_stock(182).build();
//        Product product9 = Product.builder().id(9L).category(category2).product_brand("모두의프렌즈").funding_count(90).product_like_count(19200).product_name("테스트자").product_price(43000L).product_stock(182).build();
//        Product product111 = Product.builder().category(category5).product_brand("모두의프렌즈").product_like_count(10200).product_name("상품111").product_price(13000L).product_stock(182).build();
//
//        ProductImg build1 = ProductImg.builder().img_code(ImgCode.BANNER).product(product1).file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\1.jpg", null)).build();
//        ProductImg build2 = ProductImg.builder().img_code(ImgCode.THUMBNAIL).product(product1).file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\2.jpg", null)).build();
//        ProductImg build3 = ProductImg.builder().img_code(ImgCode.SUB).product(product1).file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\3.jpg", null)).build();
//        ProductImg build4 = ProductImg.builder().img_code(ImgCode.SUB).product(product6).file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\4.jpg", null)).build();
//        ProductImg build5 = ProductImg.builder().img_code(ImgCode.SUB).product(product7).file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\2.jpg", null)).build();
//        ProductImg build6 = ProductImg.builder().img_code(ImgCode.THUMBNAIL).product(product3).file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\3.jpg", null)).build();
//        ProductImg build7 = ProductImg.builder().img_code(ImgCode.SUB).product(product4).file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\4.jpg", null)).build();
//        ProductImg build8 = ProductImg.builder().img_code(ImgCode.SUB).product(product5).file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\5.jpg", null)).build();
//        ProductImg build9 = ProductImg.builder().img_code(ImgCode.THUMBNAIL).product(product3).file_info(new File_info("/product/3/thumbnail1.png", "thumbnai1.png")).build();
//        ProductImg build10 = ProductImg.builder().img_code(ImgCode.SUB).product(product3).file_info(new File_info("/product/3/thumbnail1.png", "thumbnai1.png")).build();
//        ProductImg build11 = ProductImg.builder().img_code(ImgCode.MAIN).product(product3).file_info(new File_info("/product/3/thumbnail1.png", "thumbnai1.png")).build();
//        ProductImg build12 = ProductImg.builder().img_code(ImgCode.SUB).product(product4).file_info(new File_info("/product/4/thumbnail1.jpg", "sub1.jpg")).build();
//        ProductImg build13 = ProductImg.builder().img_code(ImgCode.MAIN).product(product4).file_info(new File_info("/product/4/sub1.jpg", "sub1.jpg")).build();
//        ProductImg build14 = ProductImg.builder().img_code(ImgCode.THUMBNAIL).product(product4).file_info(new File_info("/product/4/sub1.jpg", "sub1.jpg")).build();
//        ProductImg build15 = ProductImg.builder().img_code(ImgCode.THUMBNAIL).product(product5).file_info(new File_info("/product/5/sub1.jpg", "sub1.jpg")).build();
//        ProductImg build16 = ProductImg.builder().img_code(ImgCode.MAIN).product(product5).file_info(new File_info("/product/5/sub1.jpg", "sub1.jpg")).build();
//        ProductImg build17 = ProductImg.builder().img_code(ImgCode.SUB).product(product5).file_info(new File_info("/product/5/thumbnail1.jpg", "sub1.jpg")).build();
//        ProductImg build18 = ProductImg.builder().img_code(ImgCode.THUMBNAIL).product(product111).file_info(new File_info("/product/111/thumbnail1.jpg", "thumbnail1.jpg")).build();
//        ProductImg build19 = ProductImg.builder().img_code(ImgCode.SUB).product(product111).file_info(new File_info("/product/111/sub1.jpg", "sub1.jpg")).build();
//        ProductImg build20 = ProductImg.builder().img_code(ImgCode.SUB).product(product111).file_info(new File_info("/product/111/sub2.jpg", "sub2.jpg")).build();
//        ProductImg build21 = ProductImg.builder().img_code(ImgCode.MAIN).product(product111).file_info(new File_info("/product/111/main1.jpg", "main1.jpg")).build();
//        ProductImg build22 = ProductImg.builder().img_code(ImgCode.MAIN).product(product111).file_info(new File_info("/product/111/main2.jpg", "main2.jpg")).build();
//
//
//
//        categoryList.add(category1);
//        categoryList.add(category2);
//        categoryList.add(category3);
//        categoryList.add(category4);
//        categoryList.add(category5);
//
//        productList.add(product1);
//        productList.add(product2);
//        productList.add(product3);
//        productList.add(product4);
//        productList.add(product5);
//        productList.add(product6);
//        productList.add(product7);
//        productList.add(product8);
//        productList.add(product9);
//
//        productImgList.add(build1);
//        productImgList.add(build2);
//        productImgList.add(build3);
//        productImgList.add(build4);
//        productImgList.add(build5);
//        productImgList.add(build6);
//        productImgList.add(build7);
//        productImgList.add(build8);
//        productImgList.add(build9);
//        productImgList.add(build10);
//        productImgList.add(build11);
//        productImgList.add(build12);
//        productImgList.add(build13);
//        productImgList.add(build14);
//        productImgList.add(build15);
//        productImgList.add(build16);
//        productImgList.add(build17);
//        productImgList.add(build18);
//        productImgList.add(build19);
//        productImgList.add(build20);
//        productImgList.add(build21);
//        productImgList.add(build22);
//
//        categoryRepo.saveAll(categoryList);
//        productRepo.saveAll(productList);
//        productImgRepo.saveAll(productImgList);
//
//    }
    void insertFunding() {
        List<Funding> fundingList = new ArrayList<>();

        Funding funding1 = Funding.builder().funding_target_money(productList.get(1).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2022,01,28,00,00)).funding_title("내생일선물").funding_type(FundingType.FUNDING).funding_people_count(10).member(memberList.get(1)).product(productList.get(1)).build();
        Funding funding2 = Funding.builder().funding_target_money(productList.get(2).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2022,03,28,00,00)).funding_title("그냥 갖고싶어서").funding_type(FundingType.FUNDING).funding_people_count(3).member(memberList.get(0)).product(productList.get(2)).build();
        Funding funding3 = Funding.builder().funding_target_money(productList.get(3).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,11,28,00,00)).funding_title("후원합니다").funding_type(FundingType.SUPPORT).funding_people_count(22).member(memberList.get(3)).product(productList.get(3)).build();

        fundingList.add(funding1);
        fundingList.add(funding2);
        fundingList.add(funding3);

        fundingRepo.saveAll(fundingList);


    }
    void insertLike(){
        List<Like> likeList = new ArrayList<>();
//        Like like1 = Like.builder().member_id.(memberList.get(1).getId()).product_id(productList.get(1).getId()).build();
        Like like1 = Like.builder().member(memberList.get(0)).product(productList.get(0)).build();
        Like like2 = Like.builder().member(memberList.get(0)).product(productList.get(2)).build();
        Like like3 = Like.builder().member(memberList.get(0)).product(productList.get(5)).build();
        Like like4 = Like.builder().member(memberList.get(1)).product(productList.get(5)).build();
        Like like5 = Like.builder().member(memberList.get(3)).product(productList.get(5)).build();


        likeList.add(like1);
        likeList.add(like2);
        likeList.add(like3);
        likeList.add(like4);
        likeList.add(like5);


        likeRepo.saveAll(likeList);
    }
}
