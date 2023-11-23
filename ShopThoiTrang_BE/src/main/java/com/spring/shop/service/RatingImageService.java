package com.spring.shop.service;

import com.spring.shop.entity.Rating;
import com.spring.shop.entity.RatingImage;
import com.spring.shop.repository.RatingImageRepository;
import com.spring.shop.request.RatingImageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingImageService {
    @Autowired
     RatingImageRepository repository;

    public RatingImage add(RatingImageRequest request){
        RatingImage ratingImage = new RatingImage();
        ratingImage.setUrl(request.getUrl());
        ratingImage.setRating(Rating.builder().Id(request.getIdRating()).build());
        return repository.save(ratingImage);
    }
}
