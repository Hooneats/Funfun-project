package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.product.ProductListVo;
import com.kosmo.funfunhaejwo.jpa.domain.*;
import com.kosmo.funfunhaejwo.jpa.domain.semi.ImgCode;
import com.kosmo.funfunhaejwo.jpa.repository.CategoryRepo;
import com.kosmo.funfunhaejwo.jpa.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;


    List<ProductListVo> productListVo = new ArrayList<>();

    @Override
    public List<ProductListVo> getByCategory(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByCategory(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);
        }
        return productListVo;
    }

    @Override
    public List<ProductListVo> getByCategory1(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByCategory1(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);
        }
        return productListVo;
    }

    @Override
    public List<ProductListVo> getByCategory2(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByCategory2(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);
        }
        return productListVo;
    }

    @Override
    public List<ProductListVo> getByCategory3(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByCategory3(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);
        }
        return productListVo;
    }

    @Override
    public List<ProductListVo> getByCategory4(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByCategory4(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);
        }
        return productListVo;
    }

    @Override
    public List<ProductListVo> getByCount(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByCount(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);
        }
        return productListVo;
    }

    @Override
    public List<ProductListVo> getByCount1(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByCount1(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);
        }
        return productListVo;
    }

    @Override
    public List<ProductListVo> getByCount2(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByCount2(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);
        }
        return productListVo;
    }

    @Override
    public List<ProductListVo> getByCount3(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByCount3(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);
        }
        return productListVo;
    }

    @Override
    public List<ProductListVo> getByCount4(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByCount4(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);
        }
        return productListVo;
    }

    @Override
    public List<ProductListVo> getByHigh(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByHigh(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);

        }

        return productListVo;
    }

    @Override
    public List<ProductListVo> getByHigh1(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByHigh1(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);

        }

        return productListVo;
    }
    @Override
    public List<ProductListVo> getByHigh2(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByHigh2(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);

        }

        return productListVo;
    }

    @Override
    public List<ProductListVo> getByHigh3(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByHigh3(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);

        }

        return productListVo;
    }

    @Override
    public List<ProductListVo> getByHigh4(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByHigh4(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);

        }

        return productListVo;
    }

    @Override
    public List<ProductListVo> getByLow(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByLow(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);

        }

        return productListVo;
    }

    @Override
    public List<ProductListVo> getByLow1(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByLow1(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);

        }

        return productListVo;
    }

    @Override
    public List<ProductListVo> getByLow2(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByLow2(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);

        }

        return productListVo;
    }

    @Override
    public List<ProductListVo> getByLow3(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByLow3(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);

        }

        return productListVo;
    }

    @Override
    public List<ProductListVo> getByLow4(long category_id) {
        productListVo.clear();
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new IllegalArgumentException("카테고리 아이디를 확인해 주세요"));
        List<Product> productList = productRepo.findByLow4(category);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .categoryId(product.getCategory().getId())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);

        }

        return productListVo;
    }

    @Override
    public List<ProductListVo> getSearchList(String product_name) {
        productListVo.clear();
        List<Product> productList = productRepo.productSearch(product_name);
        for (Product product : productList) {
            ProductListVo productVo = ProductListVo
                    .builder()
                    .productId(product.getId())
                    .brand(product.getProduct_brand())
                    .likeRate(product.getProduct_like_count())
                    .title(product.getProduct_name())
                    .price(product.getProduct_price())
                    .fundingCount(product.getFunding_count())
                    .brand(product.getProduct_brand())
                    .build();
            for (ProductImg productImg : product.getProductImgs()) {
                if (productImg.getImg_code().name().equals(ImgCode.SUB.name())) {
                    productVo.setSrc(productImg.getFile_info().getFile_src());
                }
            }
            productListVo.add(productVo);

        }

        return productListVo;
    }
}
