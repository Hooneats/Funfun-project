package com.kosmo.funfunhaejwo;


import com.kosmo.funfunhaejwo.jpa.domain.*;
import com.kosmo.funfunhaejwo.jpa.domain.semi.File_info;
import com.kosmo.funfunhaejwo.jpa.domain.semi.ImgCode;
import com.kosmo.funfunhaejwo.jpa.domain.semi.LoginApi;
import com.kosmo.funfunhaejwo.jpa.domain.semi.Role;
import com.kosmo.funfunhaejwo.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Init {

    private final MemberRepo memberRepo;
    private final ProfileImgRepo profileImgRepo;
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    private final ProductImgRepo productImgRepo;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void insertInitDB() {
        profileImgRepo.deleteAll();
        memberRepo.deleteAll();
        productImgRepo.deleteAll();
        productRepo.deleteAll();
        categoryRepo.deleteAll();


        insertMember();
        insertProduct();

    }


    void insertMember() {
        List<Member> memberList = new ArrayList<>();
        List<ProfileImg> profileImgList = new ArrayList<>();

        ProfileImg build1 = ProfileImg.builder().file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\1.jpg", null, null)).build();
        ProfileImg build2 = ProfileImg.builder().file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\2.jpg", null, null)).build();
        ProfileImg build3 = ProfileImg.builder().file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\3.jpg", null, null)).build();
        ProfileImg build4 = ProfileImg.builder().file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\4.jpg", null, null)).build();
        ProfileImg build5 = ProfileImg.builder().file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\5.jpg", null, null)).build();
        Member member5 = Member.builder().email("kmh5@naver.com").password(passwordEncoder.encode("1234")).nic_name("나루토5").profile_img(build5).login_api(LoginApi.EMAIL).role(Role.USER).phone_number("0107777777").build();
        Member member4 = Member.builder().email("kmh4@naver.com").password(passwordEncoder.encode("1234")).nic_name("나루토4").profile_img(build5).login_api(LoginApi.EMAIL).role(Role.USER).phone_number("0107777777").build();
        Member member3 = Member.builder().email("kmh3@naver.com").password(passwordEncoder.encode("1234")).nic_name("나루토3").profile_img(build5).login_api(LoginApi.EMAIL).role(Role.USER).phone_number("0107777777").build();
        Member member2 = Member.builder().email("kmh2@naver.com").password(passwordEncoder.encode("1234")).nic_name("나루토2").profile_img(build5).login_api(LoginApi.EMAIL).role(Role.USER).phone_number("0107777777").build();
        Member member1 = Member.builder().email("kmh1@naver.com").password(passwordEncoder.encode("1234")).nic_name("나루토1").profile_img(build5).login_api(LoginApi.EMAIL).role(Role.USER).phone_number("0107777777").build();

        profileImgList.add(build1);
        profileImgList.add(build2);
        profileImgList.add(build3);
        profileImgList.add(build4);
        profileImgList.add(build5);
        profileImgRepo.saveAll(profileImgList);

        memberList.add(member1);
        memberList.add(member2);
        memberList.add(member3);
        memberList.add(member4);
        memberList.add(member5);
        memberRepo.saveAll(memberList);
    }

    void insertProduct() {
        List<ProductImg> productImgList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();

        Category category1 = new Category(null, "김규민프렌즈");
        Category category2 = new Category(null, "김동원프렌즈");
        Category category3 = new Category(null, "신대현프렌즈");
        Category category4 = new Category(null, "신혜림프렌즈");
        Category category5 = new Category(null, "이지연프렌즈");

        Product product1 = Product.builder().category(category1).product_brand("모두의프렌즈").product_detail("아주 사랑스러워요").product_like_count(10000).product_name("상품1").product_price(38000L).product_stock(182).build();
        Product product2 = Product.builder().category(category2).product_brand("모두의프렌즈").product_detail("아주 사랑스러워요").product_like_count(8000).product_name("상품2").product_price(56000L).product_stock(182).build();
        Product product3 = Product.builder().category(category3).product_brand("모두의프렌즈").product_detail("아주 사랑스러워요").product_like_count(50).product_name("상품3").product_price(1623000L).product_stock(182).build();
        Product product4 = Product.builder().category(category4).product_brand("모두의프렌즈").product_detail("아주 사랑스러워요").product_like_count(900).product_name("상품4").product_price(1450000L).product_stock(182).build();
        Product product5 = Product.builder().category(category5).product_brand("모두의프렌즈").product_detail("아주 사랑스러워요").product_like_count(10200).product_name("상품5").product_price(13000L).product_stock(182).build();

        ProductImg build1 = ProductImg.builder().img_code(ImgCode.BANNER).product(product1).file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\1.jpg", null, null)).build();
        ProductImg build2 = ProductImg.builder().img_code(ImgCode.THUMBNAIL).product(product1).file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\2.jpg", null, null)).build();
        ProductImg build3 = ProductImg.builder().img_code(ImgCode.SUB).product(product1).file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\3.jpg", null, null)).build();
        ProductImg build4 = ProductImg.builder().img_code(ImgCode.SUB).product(product1).file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\4.jpg", null, null)).build();
        ProductImg build5 = ProductImg.builder().img_code(ImgCode.SUB).product(product2).file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\2.jpg", null, null)).build();
        ProductImg build6 = ProductImg.builder().img_code(ImgCode.THUMBNAIL).product(product3).file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\3.jpg", null, null)).build();
        ProductImg build7 = ProductImg.builder().img_code(ImgCode.SUB).product(product4).file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\4.jpg", null, null)).build();
        ProductImg build8 = ProductImg.builder().img_code(ImgCode.SUB).product(product5).file_info(new File_info("C:\\Users\\MyDev\\Desktop\\KosMo\\final\\funfunhaejwo\\img\\5.jpg", null, null)).build();

        categoryList.add(category1);
        categoryList.add(category2);
        categoryList.add(category3);
        categoryList.add(category4);
        categoryList.add(category5);

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);

        productImgList.add(build1);
        productImgList.add(build2);
        productImgList.add(build3);
        productImgList.add(build4);
        productImgList.add(build5);
        productImgList.add(build6);
        productImgList.add(build7);
        productImgList.add(build8);

        categoryRepo.saveAll(categoryList);
        productRepo.saveAll(productList);
        productImgRepo.saveAll(productImgList);

    }
}
