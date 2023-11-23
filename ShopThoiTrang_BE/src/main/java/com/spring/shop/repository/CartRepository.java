package com.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.shop.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query(value = "Select e from Cart  e where e.Id = :id")
    public Cart getByIdCart(@Param("id") Integer id);
    @Query(value = "Select e from Cart  e where e.customer.Id = :id")
    public Cart getByIdCustomer(@Param("id") Integer id);
}
