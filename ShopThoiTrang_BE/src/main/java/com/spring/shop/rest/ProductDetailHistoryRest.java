package com.spring.shop.rest;

import com.spring.shop.entity.ProductDetailHistory;
import com.spring.shop.request.ProductDetailHistoryRequest;
import com.spring.shop.service.ProductDetailHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/productdetailhistory")
public class ProductDetailHistoryRest {
    @Autowired
    ProductDetailHistoryService service;
    @PostMapping
    public ResponseEntity<?> add(@RequestBody ProductDetailHistoryRequest productDetailHistory){
        return ResponseEntity.ok(service.add(productDetailHistory));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.getAllById(id));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getbyid(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.getById(id));
    }
}
