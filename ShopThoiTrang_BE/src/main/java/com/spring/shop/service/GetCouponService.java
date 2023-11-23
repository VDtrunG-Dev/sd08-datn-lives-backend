package com.spring.shop.service;

import com.spring.shop.entity.Coupon;
import com.spring.shop.repository.GetCouponRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCouponService {
    @Autowired
    private GetCouponRepo repo;

    public List<Coupon> getByCustomer(Integer id){
        return repo.getByCustomer(id);
    }
}
