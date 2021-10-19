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
import java.util.Random;
import java.util.UUID;

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
    private final FriendListTableRepo friendListTableRepo;

    private final PasswordEncoder passwordEncoder;

    private final FundingRepo fundingRepo;

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
//        likeRepo.deleteAll();
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
        insertMember();
        insetFriend();
        insertFunding();
        insertOrder();
        insertLike();

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
        List<Funding> byMember_id3 = fundingRepo.findByMember_id(3L);
        List<Funding> byMember_id2 = fundingRepo.findByMember_id(2L);
        List<Funding> byMember_id1 = fundingRepo.findByMember_id(1L);
        Funding funding1 = fundingRepo.findById(95L).orElse(null);
        Funding funding5 = fundingRepo.findById(96L).orElse(null);
        Funding funding6 = fundingRepo.findById(97L).orElse(null);

        Order build1 = Order.builder()
                .id(4L)
                .member(hoo)
                .funding(byMember_id3.get(0))
                .product(product1)
                .total_payment(50000L)
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.SUCCESS)
                .build();
        Order build2 = Order.builder()
                .id(5L)
                .member(hoo)
                .funding(byMember_id2.get(0))
                .product(product1)
                .total_payment(50000L)
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.SUCCESS)
                .build();
        Order build3 = Order.builder()
                .id(6L)
                .member(hoo)
                .funding(byMember_id1.get(0))
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
//    void insertLike(){
//        List<Like> likeList = new ArrayList<>();
////        Like like1 = Like.builder().member_id.(memberList.get(1).getId()).product_id(productList.get(1).getId()).build();
//        Like like1 = Like.builder().member(memberList.get(0)).product(productList.get(0)).build();
//        Like like2 = Like.builder().member(memberList.get(0)).product(productList.get(2)).build();
//        Like like3 = Like.builder().member(memberList.get(0)).product(productList.get(5)).build();
//        Like like4 = Like.builder().member(memberList.get(1)).product(productList.get(5)).build();
//        Like like5 = Like.builder().member(memberList.get(3)).product(productList.get(5)).build();
//
//
//        likeList.add(like1);
//        likeList.add(like2);
//        likeList.add(like3);
//        likeList.add(like4);
//        likeList.add(like5);
//
//
//        likeRepo.saveAll(likeList);
//    }


    void insertMember() {
//        Member member = memberRepo.findByEmail("hooneats@naver.com").orElse(null);
//        if (member != null) {
//            ProfileImg byMember = profileImgRepo.findByMember(member).orElse(null);
//            if (byMember != null) {
//                profileImgRepo.delete(byMember);
//            }
//            orderRepo.deleteAll();
//            fundingRepo.deleteAll();
//            memberRepo.delete(member);
//        }
        List<Member> memberList = new ArrayList<>();
        Member member1 = Member.builder().id(1L).email("test0@naver.com").password(passwordEncoder.encode("123456789asd!")).nic_name("test0").login_api(LoginApi.EMAIL).role(Role.USER).build();
        Member member2 = Member.builder().id(2L).email("test1@naver.com").password(passwordEncoder.encode("123456789asd!")).nic_name("test1").login_api(LoginApi.EMAIL).role(Role.USER).build();
        Member member3 = Member.builder().id(3L).email("test2@naver.com").password(passwordEncoder.encode("123456789asd!")).nic_name("test2").login_api(LoginApi.EMAIL).role(Role.USER).build();
        Member member4 = Member.builder().id(4L).email("hooneats@naver.com").password(passwordEncoder.encode("123456789asd!")).nic_name("후니츠").login_api(LoginApi.EMAIL).role(Role.ADMIN).phone_number("01077712027").build();
        Member member5 = Member.builder().id(5L).email("test3@naver.com").password(passwordEncoder.encode("123456789asd!")).nic_name("test3").login_api(LoginApi.EMAIL).role(Role.USER).build();
        Member member6 = Member.builder().id(6L).email("test4@naver.com").password(passwordEncoder.encode("123456789asd!")).nic_name("test4").login_api(LoginApi.EMAIL).role(Role.USER).build();
        Member member7 = Member.builder().id(7L).email("test5@naver.com").password(passwordEncoder.encode("123456789asd!")).nic_name("test5").login_api(LoginApi.EMAIL).role(Role.USER).build();
        Member member8 = Member.builder().id(8L).email("test6@naver.com").password(passwordEncoder.encode("123456789asd!")).nic_name("test6").login_api(LoginApi.EMAIL).role(Role.USER).build();
//        Member savedMember = memberRepo.save(member1);
        memberList.add(member1);
        memberList.add(member2);
        memberList.add(member3);
        memberList.add(member4);
        memberList.add(member5);
        memberList.add(member6);
        memberList.add(member7);
        memberList.add(member8);
        memberRepo.saveAll(memberList);

        List<ProfileImg> profileImgList = new ArrayList<>();
        ProfileImg buildProfile1 = ProfileImg.builder().id(1L).member(member1).file_info(new File_info("profile/profile.jpg", "profile.jpg")).build();
        ProfileImg buildProfile2 = ProfileImg.builder().id(2L).member(member2).file_info(new File_info("profile/profile.jpg", "profile.jpg")).build();
        ProfileImg buildProfile3 = ProfileImg.builder().id(3L).member(member3).file_info(new File_info("profile/profile.jpg", "profile.jpg")).build();
        ProfileImg buildProfile4 = ProfileImg.builder().id(4L).member(member4).file_info(new File_info("profile/profile.jpg", "profile.jpg")).build();
        ProfileImg buildProfile5 = ProfileImg.builder().id(5L).member(member5).file_info(new File_info("profile/profile.jpg", "profile.jpg")).build();
        ProfileImg buildProfile6 = ProfileImg.builder().id(6L).member(member6).file_info(new File_info("profile/profile.jpg", "profile.jpg")).build();
        ProfileImg buildProfile7 = ProfileImg.builder().id(7L).member(member7).file_info(new File_info("profile/profile.jpg", "profile.jpg")).build();
        ProfileImg buildProfile8 = ProfileImg.builder().id(8L).member(member8).file_info(new File_info("profile/profile.jpg", "profile.jpg")).build();
//        profileImgRepo.save(buildProfile);
        profileImgList.add(buildProfile1);
        profileImgList.add(buildProfile2);
        profileImgList.add(buildProfile3);
        profileImgList.add(buildProfile4);
        profileImgList.add(buildProfile5);
        profileImgList.add(buildProfile6);
        profileImgList.add(buildProfile7);
        profileImgList.add(buildProfile8);
        profileImgRepo.saveAll(profileImgList);
    }
    void insetFriend() {
        List<FriendListTable> friendListTableList = new ArrayList<>();
        List<Member> memberList = memberRepo.findAll();
        FriendListTable build1 = FriendListTable.builder().id(1L).member(memberList.get(0)).friend(memberList.get(1)).build();
        FriendListTable build2 = FriendListTable.builder().id(2L).member(memberList.get(1)).friend(memberList.get(2)).build();
        FriendListTable build3 = FriendListTable.builder().id(3L).member(memberList.get(2)).friend(memberList.get(3)).build();
        FriendListTable build4 = FriendListTable.builder().id(4L).member(memberList.get(3)).friend(memberList.get(4)).build();
        FriendListTable build5 = FriendListTable.builder().id(5L).member(memberList.get(4)).friend(memberList.get(5)).build();
        FriendListTable build6 = FriendListTable.builder().id(6L).member(memberList.get(5)).friend(memberList.get(6)).build();
        FriendListTable build7 = FriendListTable.builder().id(7L).member(memberList.get(6)).friend(memberList.get(7)).build();
        FriendListTable build10 = FriendListTable.builder().id(10L).member(memberList.get(0)).friend(memberList.get(1)).build();
        FriendListTable build11 = FriendListTable.builder().id(11L).member(memberList.get(1)).friend(memberList.get(2)).build();
        FriendListTable build12 = FriendListTable.builder().id(12L).member(memberList.get(2)).friend(memberList.get(3)).build();
        FriendListTable build13 = FriendListTable.builder().id(13L).member(memberList.get(3)).friend(memberList.get(4)).build();
        FriendListTable build14 = FriendListTable.builder().id(14L).member(memberList.get(4)).friend(memberList.get(5)).build();
        FriendListTable build15 = FriendListTable.builder().id(15L).member(memberList.get(5)).friend(memberList.get(6)).build();
        FriendListTable build16 = FriendListTable.builder().id(16L).member(memberList.get(6)).friend(memberList.get(7)).build();

        friendListTableList.add(build1);
        friendListTableList.add(build2);
        friendListTableList.add(build3);
        friendListTableList.add(build4);
        friendListTableList.add(build5);
        friendListTableList.add(build6);
        friendListTableList.add(build7);
        friendListTableList.add(build10);
        friendListTableList.add(build11);
        friendListTableList.add(build12);
        friendListTableList.add(build13);
        friendListTableList.add(build14);
        friendListTableList.add(build15);
        friendListTableList.add(build16);

        friendListTableRepo.saveAll(friendListTableList);
    }
    void insertFunding() {
        List<Funding> fundingList = new ArrayList<>();
        List<Member> memberList = memberRepo.findAll();
        List<Product> productList = productRepo.findAll();
        Funding funding1 = Funding.builder().id(1L).funding_collected_money(5000L).funding_target_money(productList.get(1).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,10,27,00,00)).funding_title("내생일선물").funding_type(FundingType.FUNDING).funding_people_count(20).member(memberList.get(1)).product(productList.get(1)).build();
        Funding funding2 = Funding.builder().id(2L).funding_collected_money(50000L).funding_target_money(productList.get(2).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,10,28,00,00)).funding_title("그냥 갖고싶어서").funding_type(FundingType.FUNDING).funding_people_count(16).member(memberList.get(0)).product(productList.get(2)).build();
        Funding funding3 = Funding.builder().id(3L).funding_collected_money(25000L).funding_target_money(productList.get(3).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,10,29,00,00)).funding_title("참여해주는 그대 사랑해").funding_type(FundingType.SUPPORT).funding_people_count(22).member(memberList.get(3)).product(productList.get(3)).build();
        Funding funding4 = Funding.builder().id(4L).funding_collected_money(300000L).funding_target_money(productList.get(4).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,11,01,00,00)).funding_title("정말 꼭 필요해요.").funding_type(FundingType.SUPPORT).funding_people_count(25).member(memberList.get(3)).product(productList.get(4)).build();
        Funding funding5 = Funding.builder().id(5L).funding_collected_money(3200L).funding_target_money(productList.get(5).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,11,03,00,00)).funding_title("너도 나도 갖구싶죠?").funding_type(FundingType.SUPPORT).funding_people_count(10).member(memberList.get(2)).product(productList.get(5)).build();
        Funding funding6 = Funding.builder().id(6L).funding_collected_money(330000L).funding_target_money(productList.get(6).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,11,07,00,00)).funding_title("이거 이쁘다고 생각하는사람 천원씩~").funding_type(FundingType.SUPPORT).funding_people_count(15).member(memberList.get(1)).product(productList.get(6)).build();
        Funding funding7 = Funding.builder().id(7L).funding_collected_money(10000L).funding_target_money(productList.get(7).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,11,15,00,00)).funding_title("돈이 요즘 없어서...").funding_type(FundingType.SUPPORT).funding_people_count(16).member(memberList.get(0)).product(productList.get(7)).build();
        Funding funding8 = Funding.builder().id(8L).funding_collected_money(15000L).funding_target_money(productList.get(8).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,11,18,00,00)).funding_title("너네 월급탄거안다.").funding_type(FundingType.SUPPORT).funding_people_count(18).member(memberList.get(5)).product(productList.get(8)).build();
        Funding funding9 = Funding.builder().id(9L).funding_collected_money(45000L).funding_target_money(productList.get(9).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,11,20,00,00)).funding_title("나만 백수자나...").funding_type(FundingType.SUPPORT).funding_people_count(11).member(memberList.get(6)).product(productList.get(9)).build();
        Funding funding10 = Funding.builder().id(10L).funding_collected_money(32000L).funding_target_money(productList.get(100).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,11,28,00,00)).funding_title("이정도는 생일빵").funding_type(FundingType.SUPPORT).funding_people_count(10).member(memberList.get(4)).product(productList.get(100)).build();
        Funding funding11 = Funding.builder().id(11L).funding_collected_money(65000L).funding_target_money(productList.get(101).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,11,28,00,00)).funding_title("결혼해 나 축하해줘").funding_type(FundingType.SUPPORT).funding_people_count(35).member(memberList.get(3)).product(productList.get(101)).build();
        Funding funding12 = Funding.builder().id(12L).funding_collected_money(90000L).funding_target_money(productList.get(102).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,12,1,00,00)).funding_title("이번에 취업했옹! 도와주라").funding_type(FundingType.SUPPORT).funding_people_count(5).member(memberList.get(2)).product(productList.get(102)).build();
        Funding funding13 = Funding.builder().id(13L).funding_collected_money(100000L).funding_target_money(productList.get(103).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,12,5,00,00)).funding_title("투자좀 합시다 나에게..").funding_type(FundingType.SUPPORT).funding_people_count(2).member(memberList.get(2)).product(productList.get(103)).build();
        Funding funding14 = Funding.builder().id(14L).funding_collected_money(110000L).funding_target_money(productList.get(104).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,12,6,00,00)).funding_title("불우한 나를 도와주세요").funding_type(FundingType.SUPPORT).funding_people_count(1).member(memberList.get(2)).product(productList.get(104)).build();
        Funding funding15 = Funding.builder().id(15L).funding_collected_money(15000L).funding_target_money(productList.get(105).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,12,10,00,00)).funding_title("보여줘 너의 마음").funding_type(FundingType.SUPPORT).funding_people_count(5).member(memberList.get(1)).product(productList.get(105)).build();
        Funding funding16 = Funding.builder().id(16L).funding_collected_money(150000L).funding_target_money(productList.get(106).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,12,11,00,00)).funding_title("갚을께! 일단 도와줘").funding_type(FundingType.SUPPORT).funding_people_count(7).member(memberList.get(1)).product(productList.get(106)).build();
        Funding funding17 = Funding.builder().id(17L).funding_collected_money(10000L).funding_target_money(productList.get(107).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,12,13,00,00)).funding_title("지갑이 비었네?").funding_type(FundingType.SUPPORT).funding_people_count(77).member(memberList.get(1)).product(productList.get(107)).build();
        Funding funding18 = Funding.builder().id(18L).funding_collected_money(220000L).funding_target_money(productList.get(108).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,12,15,00,00)).funding_title("천원씩만 내주라").funding_type(FundingType.SUPPORT).funding_people_count(44).member(memberList.get(0)).product(productList.get(108)).build();
        Funding funding19 = Funding.builder().id(19L).funding_collected_money(33000L).funding_target_money(productList.get(109).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,12,16,00,00)).funding_title("만원씩만 내주라").funding_type(FundingType.SUPPORT).funding_people_count(4).member(memberList.get(0)).product(productList.get(109)).build();
        Funding funding20 = Funding.builder().id(20L).funding_collected_money(39000L).funding_target_money(productList.get(150).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,12,18,00,00)).funding_title("나 인생 잘못살았니?").funding_type(FundingType.SUPPORT).funding_people_count(14).member(memberList.get(0)).product(productList.get(150)).build();
        Funding funding21 = Funding.builder().id(21L).funding_collected_money(45000L).funding_target_money(productList.get(151).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,12,22,00,00)).funding_title("친구들아'아..나 친구없지..'").funding_type(FundingType.SUPPORT).funding_people_count(2).member(memberList.get(4)).product(productList.get(151)).build();
        Funding funding22 = Funding.builder().id(22L).funding_collected_money(78000L).funding_target_money(productList.get(152).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,12,24,00,00)).funding_title("가즈아~!모아보자~!").funding_type(FundingType.SUPPORT).funding_people_count(1).member(memberList.get(4)).product(productList.get(152)).build();
        Funding funding23 = Funding.builder().id(23L).funding_collected_money(77000L).funding_target_money(productList.get(153).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,12,26,00,00)).funding_title("하하하하하하하하하").funding_type(FundingType.SUPPORT).funding_people_count(22).member(memberList.get(4)).product(productList.get(153)).build();
        Funding funding24 = Funding.builder().id(24L).funding_collected_money(44000L).funding_target_money(productList.get(154).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,12,27,00,00)).funding_title("클릭한번만~!").funding_type(FundingType.SUPPORT).funding_people_count(24).member(memberList.get(4)).product(productList.get(154)).build();
        Funding funding25 = Funding.builder().id(25L).funding_collected_money(36000L).funding_target_money(productList.get(156).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,12,28,00,00)).funding_title("친구야 진짜 친구야").funding_type(FundingType.SUPPORT).funding_people_count(6).member(memberList.get(6)).product(productList.get(156)).build();
        Funding funding26 = Funding.builder().id(26L).funding_collected_money(46000L).funding_target_money(productList.get(157).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,12,28,00,00)).funding_title("이번에 결혼해요 이게 필요해요").funding_type(FundingType.SUPPORT).funding_people_count(7).member(memberList.get(6)).product(productList.get(157)).build();
        Funding funding27 = Funding.builder().id(27L).funding_collected_money(25000L).funding_target_money(productList.get(158).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,12,30,00,00)).funding_title("이번 생일은 너로 정했어!").funding_type(FundingType.SUPPORT).funding_people_count(27).member(memberList.get(6)).product(productList.get(158)).build();
        Funding funding28 = Funding.builder().id(28L).funding_collected_money(45000L).funding_target_money(productList.get(159).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2022,1,5,00,00)).funding_title("다음번 생일선물 미리주라").funding_type(FundingType.SUPPORT).funding_people_count(9).member(memberList.get(6)).product(productList.get(159)).build();
        Funding funding29 = Funding.builder().id(29L).funding_collected_money(2000L).funding_target_money(productList.get(160).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,10,28,00,00)).funding_title("모아보자 모아보자").funding_type(FundingType.SUPPORT).funding_people_count(8).member(memberList.get(5)).product(productList.get(160)).build();
        Funding funding30 = Funding.builder().id(30L).funding_collected_money(3000L).funding_target_money(productList.get(161).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,10,29,00,00)).funding_title("내 친구가 생일이야 난 돈이없구").funding_type(FundingType.SUPPORT).funding_people_count(3).member(memberList.get(5)).product(productList.get(161)).build();
        Funding funding31 = Funding.builder().id(31L).funding_collected_money(33000L).funding_target_money(productList.get(162).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,10,22,00,00)).funding_title("기부천사에게~").funding_type(FundingType.SUPPORT).funding_people_count(6).member(memberList.get(4)).product(productList.get(162)).build();
        Funding funding32 = Funding.builder().id(32L).funding_collected_money(32000L).funding_target_money(productList.get(163).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,10,23,00,00)).funding_title("내가.. 뭐라고 했지?").funding_type(FundingType.SUPPORT).funding_people_count(3).member(memberList.get(5)).product(productList.get(163)).build();
        Funding funding33 = Funding.builder().id(33L).funding_collected_money(31000L).funding_target_money(productList.get(164).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,10,24,00,00)).funding_title("우린 깐부잖아").funding_type(FundingType.SUPPORT).funding_people_count(33).member(memberList.get(3)).product(productList.get(164)).build();
        Funding funding34 = Funding.builder().id(34L).funding_collected_money(40000L).funding_target_money(productList.get(165).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,10,25,00,00)).funding_title("너는 오늘 이걸 나에게 사줄거야").funding_type(FundingType.SUPPORT).funding_people_count(32).member(memberList.get(2)).product(productList.get(165)).build();
        Funding funding35 = Funding.builder().id(35L).funding_collected_money(4000L).funding_target_money(productList.get(166).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,10,26,00,00)).funding_title("나 오늘 대학붙었엉!축하해줘!").funding_type(FundingType.SUPPORT).funding_people_count(3).member(memberList.get(4)).product(productList.get(166)).build();
        Funding funding36 = Funding.builder().id(36L).funding_collected_money(2000L).funding_target_money(productList.get(167).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,10,27,00,00)).funding_title("내년생일을 땡겨쓴다! 어서줘!").funding_type(FundingType.SUPPORT).funding_people_count(4).member(memberList.get(3)).product(productList.get(167)).build();
        Funding funding37 = Funding.builder().id(37L).funding_collected_money(230000L).funding_target_money(productList.get(168).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,10,28,00,00)).funding_title("돈 필요하다고 했지? 난 선물필요해").funding_type(FundingType.SUPPORT).funding_people_count(22).member(memberList.get(4)).product(productList.get(168)).build();
        Funding funding38 = Funding.builder().id(38L).funding_collected_money(130000L).funding_target_money(productList.get(169).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,10,29,00,00)).funding_title("사주면 ㄳ").funding_type(FundingType.SUPPORT).funding_people_count(8).member(memberList.get(2)).product(productList.get(169)).build();
        Funding funding39 = Funding.builder().id(39L).funding_collected_money(45000L).funding_target_money(productList.get(170).getProduct_price()).funding_create_time(LocalDateTime.now()).funding_expired_time(LocalDateTime.of(2021,10,30,00,00)).funding_title("뻔뻔하지만 FunFun하게 부탁할게 사줘!").funding_type(FundingType.SUPPORT).funding_people_count(13).member(memberList.get(1)).product(productList.get(170)).build();

        fundingList.add(funding1);
        fundingList.add(funding2);
        fundingList.add(funding3);
        fundingList.add(funding4);
        fundingList.add(funding5);
        fundingList.add(funding6);
        fundingList.add(funding7);
        fundingList.add(funding8);
        fundingList.add(funding9);
        fundingList.add(funding10);
        fundingList.add(funding11);
        fundingList.add(funding12);
        fundingList.add(funding13);
        fundingList.add(funding14);
        fundingList.add(funding15);
        fundingList.add(funding16);
        fundingList.add(funding17);
        fundingList.add(funding18);
        fundingList.add(funding19);
        fundingList.add(funding20);
        fundingList.add(funding21);
        fundingList.add(funding22);
        fundingList.add(funding23);
        fundingList.add(funding24);
        fundingList.add(funding25);
        fundingList.add(funding26);
        fundingList.add(funding27);
        fundingList.add(funding28);
        fundingList.add(funding29);
        fundingList.add(funding30);
        fundingList.add(funding31);
        fundingList.add(funding32);
        fundingList.add(funding33);
        fundingList.add(funding34);
        fundingList.add(funding35);
        fundingList.add(funding36);
        fundingList.add(funding37);
        fundingList.add(funding38);
        fundingList.add(funding39);

        fundingRepo.saveAll(fundingList);
    }
    void insertOrder() {
        List<Funding> allFind = fundingRepo.findAll();
        List<Member> allMember = memberRepo.findAll();
        List<Order> orderList = orderRepo.findAll();

        Order order1 = Order.builder().id(1L).funding(allFind.get(0)).member(allMember.get(1)).product(allFind.get(0).getProduct()).total_payment(10000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order2 = Order.builder().id(2L).funding(allFind.get(1)).member(allMember.get(2)).product(allFind.get(1).getProduct()).total_payment(20000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order3 = Order.builder().id(3L).funding(allFind.get(2)).member(allMember.get(0)).product(allFind.get(2).getProduct()).total_payment(30000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order4 = Order.builder().id(4L).funding(allFind.get(3)).member(allMember.get(2)).product(allFind.get(3).getProduct()).total_payment(40000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order5 = Order.builder().id(5L).funding(allFind.get(4)).member(allMember.get(1)).product(allFind.get(4).getProduct()).total_payment(50000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order6 = Order.builder().id(6L).funding(allFind.get(5)).member(allMember.get(3)).product(allFind.get(5).getProduct()).total_payment(1000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order7 = Order.builder().id(7L).funding(allFind.get(6)).member(allMember.get(4)).product(allFind.get(6).getProduct()).total_payment(2000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order8 = Order.builder().id(8L).funding(allFind.get(7)).member(allMember.get(1)).product(allFind.get(7).getProduct()).total_payment(3000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order9 = Order.builder().id(9L).funding(allFind.get(8)).member(allMember.get(5)).product(allFind.get(8).getProduct()).total_payment(4000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order10 = Order.builder().id(10L).funding(allFind.get(9)).member(allMember.get(6)).product(allFind.get(9).getProduct()).total_payment(5000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order11 = Order.builder().id(11L).funding(allFind.get(10)).member(allMember.get(5)).product(allFind.get(10).getProduct()).total_payment(11000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order12 = Order.builder().id(12L).funding(allFind.get(11)).member(allMember.get(6)).product(allFind.get(11).getProduct()).total_payment(12000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order13 = Order.builder().id(13L).funding(allFind.get(12)).member(allMember.get(6)).product(allFind.get(12).getProduct()).total_payment(13000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order14 = Order.builder().id(14L).funding(allFind.get(13)).member(allMember.get(4)).product(allFind.get(13).getProduct()).total_payment(14000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order15 = Order.builder().id(15L).funding(allFind.get(14)).member(allMember.get(5)).product(allFind.get(14).getProduct()).total_payment(15000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order16 = Order.builder().id(16L).funding(allFind.get(15)).member(allMember.get(3)).product(allFind.get(15).getProduct()).total_payment(21000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order17 = Order.builder().id(17L).funding(allFind.get(16)).member(allMember.get(4)).product(allFind.get(16).getProduct()).total_payment(22000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order18 = Order.builder().id(18L).funding(allFind.get(17)).member(allMember.get(2)).product(allFind.get(17).getProduct()).total_payment(23000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order19 = Order.builder().id(19L).funding(allFind.get(18)).member(allMember.get(2)).product(allFind.get(18).getProduct()).total_payment(24000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order20 = Order.builder().id(20L).funding(allFind.get(19)).member(allMember.get(3)).product(allFind.get(19).getProduct()).total_payment(25000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order21 = Order.builder().id(21L).funding(allFind.get(20)).member(allMember.get(5)).product(allFind.get(20).getProduct()).total_payment(31000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order22 = Order.builder().id(22L).funding(allFind.get(21)).member(allMember.get(6)).product(allFind.get(21).getProduct()).total_payment(32000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order23 = Order.builder().id(23L).funding(allFind.get(22)).member(allMember.get(4)).product(allFind.get(22).getProduct()).total_payment(33000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order24 = Order.builder().id(24L).funding(allFind.get(23)).member(allMember.get(4)).product(allFind.get(23).getProduct()).total_payment(34000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order25 = Order.builder().id(25L).funding(allFind.get(24)).member(allMember.get(5)).product(allFind.get(24).getProduct()).total_payment(35000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order26 = Order.builder().id(26L).funding(allFind.get(25)).member(allMember.get(3)).product(allFind.get(25).getProduct()).total_payment(11000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order27 = Order.builder().id(27L).funding(allFind.get(26)).member(allMember.get(3)).product(allFind.get(26).getProduct()).total_payment(12000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order28 = Order.builder().id(28L).funding(allFind.get(27)).member(allMember.get(2)).product(allFind.get(27).getProduct()).total_payment(55000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order29 = Order.builder().id(29L).funding(allFind.get(28)).member(allMember.get(3)).product(allFind.get(28).getProduct()).total_payment(30000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();
        Order order30 = Order.builder().id(30L).funding(allFind.get(29)).member(allMember.get(4)).product(allFind.get(29).getProduct()).total_payment(20000L).merchant_uid(UUID.randomUUID().toString()).i_port_id(UUID.randomUUID().toString().replaceAll("-", "")).pg_id(String.valueOf((int) Math.random() * 100)).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.SUCCESS).build();

        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);
        orderList.add(order6);
        orderList.add(order7);
        orderList.add(order8);
        orderList.add(order9);
        orderList.add(order10);
        orderList.add(order11);
        orderList.add(order12);
        orderList.add(order13);
        orderList.add(order14);
        orderList.add(order15);
        orderList.add(order16);
        orderList.add(order17);
        orderList.add(order18);
        orderList.add(order19);
        orderList.add(order20);
        orderList.add(order21);
        orderList.add(order22);
        orderList.add(order23);
        orderList.add(order24);
        orderList.add(order25);
        orderList.add(order26);
        orderList.add(order27);
        orderList.add(order28);
        orderList.add(order29);
        orderList.add(order30);

        orderRepo.saveAll(orderList);
    }
    void insertLike() {
        List<Member> memberList = memberRepo.findAll();
        List<Like> likeList = new ArrayList<>();
        List<Product> productList = productRepo.findAll();
//        Like like1 = Like.builder().member_id.(memberList.get(1).getId()).product_id(productList.get(1).getId()).build();
        Like like1 = Like.builder().id(1L).member(memberList.get(0)).product(productList.get(0)).build();
        Like like2 = Like.builder().id(2L).member(memberList.get(0)).product(productList.get(2)).build();
        Like like3 = Like.builder().id(3L).member(memberList.get(0)).product(productList.get(5)).build();
        Like like4 = Like.builder().id(4L).member(memberList.get(0)).product(productList.get(6)).build();
        Like like5 = Like.builder().id(5L).member(memberList.get(0)).product(productList.get(7)).build();
        Like like6 = Like.builder().id(6L).member(memberList.get(0)).product(productList.get(8)).build();
        Like like7 = Like.builder().id(7L).member(memberList.get(0)).product(productList.get(9)).build();
        Like like8 = Like.builder().id(8L).member(memberList.get(0)).product(productList.get(10)).build();
        Like like9 = Like.builder().id(9L).member(memberList.get(0)).product(productList.get(11)).build();
        Like like10 = Like.builder().id(10L).member(memberList.get(3)).product(productList.get(5)).build();
        Like like11 = Like.builder().id(11L).member(memberList.get(3)).product(productList.get(6)).build();
        Like like12 = Like.builder().id(12L).member(memberList.get(3)).product(productList.get(8)).build();
        Like like13 = Like.builder().id(13L).member(memberList.get(3)).product(productList.get(10)).build();
        Like like14 = Like.builder().id(14L).member(memberList.get(3)).product(productList.get(30)).build();
        Like like15 = Like.builder().id(15L).member(memberList.get(3)).product(productList.get(31)).build();
        Like like16 = Like.builder().id(16L).member(memberList.get(3)).product(productList.get(32)).build();
        Like like17 = Like.builder().id(17L).member(memberList.get(3)).product(productList.get(33)).build();
        Like like18 = Like.builder().id(18L).member(memberList.get(3)).product(productList.get(20)).build();
        Like like19 = Like.builder().id(19L).member(memberList.get(3)).product(productList.get(22)).build();
        Like like20 = Like.builder().id(20L).member(memberList.get(3)).product(productList.get(2)).build();
        Like like21 = Like.builder().id(21L).member(memberList.get(3)).product(productList.get(1)).build();
        Like like22 = Like.builder().id(22L).member(memberList.get(4)).product(productList.get(1)).build();
        Like like23 = Like.builder().id(23L).member(memberList.get(4)).product(productList.get(2)).build();
        Like like24 = Like.builder().id(24L).member(memberList.get(4)).product(productList.get(3)).build();
        Like like25 = Like.builder().id(25L).member(memberList.get(4)).product(productList.get(4)).build();
        Like like26 = Like.builder().id(26L).member(memberList.get(4)).product(productList.get(5)).build();
        Like like27 = Like.builder().id(27L).member(memberList.get(4)).product(productList.get(6)).build();
        Like like28 = Like.builder().id(28L).member(memberList.get(4)).product(productList.get(7)).build();
        Like like29 = Like.builder().id(29L).member(memberList.get(4)).product(productList.get(8)).build();
        Like like30 = Like.builder().id(30L).member(memberList.get(4)).product(productList.get(9)).build();
        Like like31 = Like.builder().id(31L).member(memberList.get(4)).product(productList.get(10)).build();
        Like like32 = Like.builder().id(32L).member(memberList.get(4)).product(productList.get(11)).build();
        Like like33 = Like.builder().id(33L).member(memberList.get(4)).product(productList.get(12)).build();
        Like like34 = Like.builder().id(34L).member(memberList.get(4)).product(productList.get(20)).build();
        Like like35 = Like.builder().id(35L).member(memberList.get(4)).product(productList.get(21)).build();
        Like like36 = Like.builder().id(36L).member(memberList.get(4)).product(productList.get(25)).build();
        Like like37 = Like.builder().id(37L).member(memberList.get(4)).product(productList.get(31)).build();
        Like like38 = Like.builder().id(38L).member(memberList.get(4)).product(productList.get(35)).build();
        Like like39 = Like.builder().id(39L).member(memberList.get(4)).product(productList.get(37)).build();
        Like like40 = Like.builder().id(40L).member(memberList.get(4)).product(productList.get(40)).build();
        Like like41 = Like.builder().id(41L).member(memberList.get(4)).product(productList.get(45)).build();
        Like like42 = Like.builder().id(42L).member(memberList.get(4)).product(productList.get(46)).build();
        Like like43 = Like.builder().id(43L).member(memberList.get(4)).product(productList.get(48)).build();
        Like like44 = Like.builder().id(44L).member(memberList.get(4)).product(productList.get(49)).build();
        Like like45 = Like.builder().id(45L).member(memberList.get(4)).product(productList.get(55)).build();

        productList.get(0).likeControl(true);
        productList.get(2).likeControl(true);
        productList.get(5).likeControl(true);
        productList.get(6).likeControl(true);
        productList.get(7).likeControl(true);
        productList.get(8).likeControl(true);
        productList.get(9).likeControl(true);
        productList.get(10).likeControl(true);
        productList.get(11).likeControl(true);
        productList.get(5).likeControl(true);
        productList.get(6).likeControl(true);
        productList.get(8).likeControl(true);
        productList.get(10).likeControl(true);
        productList.get(30).likeControl(true);
        productList.get(31).likeControl(true);
        productList.get(32).likeControl(true);
        productList.get(33).likeControl(true);
        productList.get(20).likeControl(true);
        productList.get(22).likeControl(true);
        productList.get(2).likeControl(true);
        productList.get(1).likeControl(true);
        productList.get(1).likeControl(true);
        productList.get(2).likeControl(true);
        productList.get(3).likeControl(true);
        productList.get(4).likeControl(true);
        productList.get(5).likeControl(true);
        productList.get(6).likeControl(true);
        productList.get(7).likeControl(true);
        productList.get(8).likeControl(true);
        productList.get(9).likeControl(true);
        productList.get(10).likeControl(true);
        productList.get(11).likeControl(true);
        productList.get(12).likeControl(true);
        productList.get(20).likeControl(true);
        productList.get(21).likeControl(true);
        productList.get(25).likeControl(true);
        productList.get(31).likeControl(true);
        productList.get(35).likeControl(true);
        productList.get(37).likeControl(true);
        productList.get(40).likeControl(true);
        productList.get(45).likeControl(true);
        productList.get(46).likeControl(true);
        productList.get(48).likeControl(true);
        productList.get(49).likeControl(true);
        productList.get(55).likeControl(true);

        productRepo.saveAll(productList);

        likeList.add(like1);
        likeList.add(like2);
        likeList.add(like3);
        likeList.add(like4);
        likeList.add(like5);
        likeList.add(like6);
        likeList.add(like7);
        likeList.add(like8);
        likeList.add(like9);
        likeList.add(like10);
        likeList.add(like11);
        likeList.add(like12);
        likeList.add(like13);
        likeList.add(like14);
        likeList.add(like15);
        likeList.add(like16);
        likeList.add(like17);
        likeList.add(like18);
        likeList.add(like19);
        likeList.add(like20);
        likeList.add(like21);
        likeList.add(like22);
        likeList.add(like23);
        likeList.add(like24);
        likeList.add(like25);
        likeList.add(like26);
        likeList.add(like27);
        likeList.add(like28);
        likeList.add(like29);
        likeList.add(like30);
        likeList.add(like31);
        likeList.add(like32);
        likeList.add(like33);
        likeList.add(like34);
        likeList.add(like35);
        likeList.add(like36);
        likeList.add(like37);
        likeList.add(like38);
        likeList.add(like39);
        likeList.add(like40);
        likeList.add(like41);
        likeList.add(like42);
        likeList.add(like43);
        likeList.add(like44);
        likeList.add(like45);

        likeRepo.saveAll(likeList);
    }
}
