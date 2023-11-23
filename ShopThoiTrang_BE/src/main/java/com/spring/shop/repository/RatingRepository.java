package com.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.shop.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {
}
