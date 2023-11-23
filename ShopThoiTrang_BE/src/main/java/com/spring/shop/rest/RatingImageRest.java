package com.spring.shop.rest;

import com.spring.shop.request.RatingImageRequest;
import com.spring.shop.service.RatingImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ratingimage")
public class RatingImageRest {
    @Autowired
    RatingImageService service;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody RatingImageRequest request){
        return ResponseEntity.ok(service.add(request));
    }
}
