package com.poly.datn.repository;

import com.poly.datn.model.TProduct;
import com.poly.datn.model.TUserAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<TProduct, Long> {

    @Query("SELECT p FROM TProduct p WHERE p.status = 1")
    Page<TProduct> findALl(Pageable pageable);

    @Query("SELECT p FROM TProduct p WHERE p.id = :id")
    TProduct findByIdProduct(Long id);

    List<TProduct> findByStatus(Integer status);

    List<TProduct> findByName(String productName);
}
