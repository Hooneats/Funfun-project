package com.kosmo.funfunhaejwo.jpa.repository;

import com.kosmo.funfunhaejwo.jpa.domain.Category;
import com.kosmo.funfunhaejwo.jpa.domain.Event;
import com.kosmo.funfunhaejwo.jpa.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long product_id);

    /*@Query("select p.product_like_count from Product p where p.id=:id")
    Long likeCount(Long product_id);*/
    @Query("select p from Product p where p.category=:category order by p.product_like_count desc")
    List<Product> findByCategory(Category category);

    @Query("select p from Product p where p.category=:category and p.product_price>10000 order by p.product_like_count desc")
    List<Product> findByCategory1(Category category);

    @Query("select p from Product p where p.category=:category and p.product_price>20000 order by p.product_like_count desc")
    List<Product> findByCategory2(Category category);

    @Query("select p from Product p where p.category=:category and p.product_price>50000 order by p.product_like_count desc")
    List<Product> findByCategory3(Category category);

    @Query("select p from Product p where p.category=:category and p.product_price>100000 order by p.product_like_count desc")
    List<Product> findByCategory4(Category category);

    @Query("select p from Product p where p.category=:category order by p.funding_count desc")
    List<Product> findByCount(Category category);

    @Query("select p from Product p where p.category=:category and p.product_price>10000 order by p.funding_count desc")
    List<Product> findByCount1(Category category);

    @Query("select p from Product p where p.category=:category and p.product_price>20000 order by p.funding_count desc")
    List<Product> findByCount2(Category category);

    @Query("select p from Product p where p.category=:category and p.product_price>50000 order by p.funding_count desc")
    List<Product> findByCount3(Category category);

    @Query("select p from Product p where p.category=:category and p.product_price>100000 order by p.funding_count desc")
    List<Product> findByCount4(Category category);

    @Query("select p from Product p where p.category=:category order by p.product_price desc")
    List<Product> findByHigh(Category category);

    @Query("select p from Product p where p.category=:category and p.product_price>10000 order by p.product_price desc")
    List<Product> findByHigh1(Category category);

    @Query("select p from Product p where p.category=:category and p.product_price>20000 order by p.product_price desc")
    List<Product> findByHigh2(Category category);

    @Query("select p from Product p where p.category=:category and p.product_price>50000 order by p.product_price desc")
    List<Product> findByHigh3(Category category);

    @Query("select p from Product p where p.category=:category and p.product_price>100000 order by p.product_price desc")
    List<Product> findByHigh4(Category category);

    @Query("select p from Product p where p.category=:category order by p.product_price asc")
    List<Product> findByLow(Category category);

    @Query("select p from Product p where p.category=:category and p.product_price>10000 order by p.product_price asc")
    List<Product> findByLow1(Category category);

    @Query("select p from Product p where p.category=:category and p.product_price>20000 order by p.product_price asc")
    List<Product> findByLow2(Category category);

    @Query("select p from Product p where p.category=:category and p.product_price>50000 order by p.product_price asc")
    List<Product> findByLow3(Category category);

    @Query("select p from Product p where p.category=:category and p.product_price>100000 order by p.product_price asc")
    List<Product> findByLow4(Category category);

    @Query("select p from Product p where p.event=:event order by p.product_price asc")
    List<Product> findByEvent(Event event);

}
