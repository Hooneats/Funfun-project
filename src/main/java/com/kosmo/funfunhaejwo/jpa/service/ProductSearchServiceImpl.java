package com.kosmo.funfunhaejwo.jpa.service;

import com.kosmo.funfunhaejwo.jpa.controller.product.ProductListVo;
import com.kosmo.funfunhaejwo.jpa.domain.Product;
import com.kosmo.funfunhaejwo.jpa.domain.ProductImg;
import com.kosmo.funfunhaejwo.jpa.domain.semi.ImgCode;
import com.kosmo.funfunhaejwo.jpa.repository.ProductSearchRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProductSearchServiceImpl implements ProductSearchService {
    private final ProductSearchRepo productSearchRepo;
    List<ProductListVo> productListVo = new ArrayList<>();

    @Override
    public List<ProductListVo> getSearchList(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.productSearch(product_name);
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

    @Override
    public List<ProductListVo> getSearchList1(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.productSearch1(product_name);
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

    @Override
    public List<ProductListVo> getSearchList2(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.productSearch2(product_name);
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

    @Override
    public List<ProductListVo> getSearchList3(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.productSearch3(product_name);
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

    @Override
    public List<ProductListVo> getSearchList4(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.productSearch4(product_name);
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

    @Override
    public List<ProductListVo> getSearchCount(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.searchCount(product_name);
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

    @Override
    public List<ProductListVo> getSearchCount1(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.searchCount1(product_name);
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

    @Override
    public List<ProductListVo> getSearchCount2(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.searchCount2(product_name);
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

    @Override
    public List<ProductListVo> getSearchCount3(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.searchCount3(product_name);
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

    @Override
    public List<ProductListVo> getSearchCount4(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.searchCount4(product_name);
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

    @Override
    public List<ProductListVo> getSearchHigh(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.searchHigh(product_name);
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

    @Override
    public List<ProductListVo> getSearchHigh1(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.searchHigh1(product_name);
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

    @Override
    public List<ProductListVo> getSearchHigh2(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.searchHigh2(product_name);
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

    @Override
    public List<ProductListVo> getSearchHigh3(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.searchHigh3(product_name);
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

    @Override
    public List<ProductListVo> getSearchHigh4(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.searchHigh4(product_name);
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

    @Override
    public List<ProductListVo> getSearchLow(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.searchLow(product_name);
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

    @Override
    public List<ProductListVo> getSearchLow1(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.searchLow1(product_name);
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

    @Override
    public List<ProductListVo> getSearchLow2(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.searchLow2(product_name);
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

    @Override
    public List<ProductListVo> getSearchLow3(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.searchLow3(product_name);
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

    @Override
    public List<ProductListVo> getSearchLow4(String product_name) {
        productListVo.clear();
        List<Product> productList = productSearchRepo.searchLow4(product_name);
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
