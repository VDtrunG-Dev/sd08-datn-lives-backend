package com.spring.shop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.shop.request.ProductDetail_MaterialRequest;
import com.spring.shop.service.ProductDetail_MaterialService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/productdetail_material")
public class ProductDetail_MaterialRest {
    @Autowired
    ProductDetail_MaterialService service;


    @PostMapping()
    public ResponseEntity<?> add(@RequestBody ProductDetail_MaterialRequest request){
        return ResponseEntity.ok(service.add(request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.ok("ok");
    }
}
