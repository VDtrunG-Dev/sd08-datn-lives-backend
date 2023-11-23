package com.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.shop.entity.Coupon;

import java.util.List;

@Repository
public interface GetCouponRepo extends JpaRepository<Coupon,Integer> {
    @Query(value = "select e from Coupon e where e.customer.Id = :id")
    public List<Coupon> getByCustomer(@Param("id") Integer id);
}
