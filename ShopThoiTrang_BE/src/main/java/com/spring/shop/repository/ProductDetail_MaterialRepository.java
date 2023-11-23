package com.spring.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.shop.entity.ProductDetail_Material;

@Repository
public interface ProductDetail_MaterialRepository extends JpaRepository<ProductDetail_Material,Integer> {

    @Query(value = "Select e from ProductDetail_Material  e where  e.productDetail.Id =:id")
    List<ProductDetail_Material> getAllById(@Param("id") Integer id);
}
