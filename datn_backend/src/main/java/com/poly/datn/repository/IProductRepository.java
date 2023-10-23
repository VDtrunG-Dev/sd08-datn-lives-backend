package com.poly.datn.repository;

import com.poly.datn.model.TProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<TProduct, Long> {
    List<TProduct> findByStatus(Integer status);

    List<TProduct> findByName(String productName);
}
