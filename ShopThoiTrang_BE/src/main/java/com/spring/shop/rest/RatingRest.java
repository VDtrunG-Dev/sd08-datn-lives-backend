package com.spring.shop.rest;

import com.spring.shop.request.DanhGiaRequest;
import com.spring.shop.service.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/rating")
public class RatingRest {
    @Autowired
    RatingService service;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody DanhGiaRequest request){
        return ResponseEntity.ok(service.add(request));
    }
}
