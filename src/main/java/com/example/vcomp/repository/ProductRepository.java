package com.example.vcomp.repository;

import com.example.vcomp.model.Product;
import com.example.vcomp.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, ProductCustomRepository{
    @Query(value = "SELECT p.id AS id," +
            "       p.name AS name," +
            "       p.description AS description," +
            "       p.price AS price," +
            "       p.category_id AS categoryId," +
            "       p.brand_id AS brandId," +
            "       p.discount AS discount," +
            "CASE WHEN lp.user_id IS NOT NULL THEN true ELSE false END AS liked " +
            "FROM product p " +
            "LEFT JOIN liked_products lp ON p.id = lp.product_id AND lp.user_id = :userId " +
            "limit :size offset :currentPage * :size", nativeQuery = true)
    List<ProductProjection> getProducts(Integer userId, Integer currentPage, Integer size);
    @Query(value = "SELECT p.id AS id," +
            "p.name AS name," +
            "p.description AS description," +
            "p.price AS price," +
            "p.category_id AS categoryId," +
            "p.brand_id AS brandId," +
            "p.discount AS discount," +
            "CASE WHEN lp.user_id IS NOT NULL THEN true ELSE false END AS liked " +
            "FROM product p " +
            "LEFT JOIN liked_products lp ON p.id = lp.product_id AND lp.user_id = :userId "+
            "LEFT JOIN viewed_products vp ON p.id = vp.product_id WHERE vp.user_id = :userId",nativeQuery = true)
    List<ProductProjection> getViewedProducts(@Param("userId") Integer userId);
}
