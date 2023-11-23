package com.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.shop.entity.Cart;
import com.spring.shop.entity.CartDetail;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail,Integer> {
    @Query(value = "Select e from CartDetail e where e.cart.customer.Id = :id")
    public List<CartDetail> getCartByCustomer(@Param("id") Integer id);
    @Query(value = "Select e from CartDetail  e where e.Id = :id")
    public CartDetail getById(@Param("id") Integer id);
    @Query(value = "Select e.Quantity from CartDetail e where e.Id = :id")
    public Integer getQuantityByCartDetail(@Param("id") Integer id);



}
