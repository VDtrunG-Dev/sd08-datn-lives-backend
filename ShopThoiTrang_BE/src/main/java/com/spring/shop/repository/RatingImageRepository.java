package com.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.shop.entity.RatingImage;

@Repository
public interface RatingImageRepository extends JpaRepository<RatingImage,Integer> {
}
