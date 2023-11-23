package com.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.shop.entity.Product_Voucher;

import java.util.List;

@Repository
public interface ProductVoucherRepository extends JpaRepository<Product_Voucher,Integer> {
    @Query("select e from Product_Voucher e where e.product.Id = :id")
    public List<Product_Voucher> getByProduct(@Param("id") Integer id);

}
