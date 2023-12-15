package com.spring.shop.rest;

import com.spring.shop.request.BrandRequest;
import com.spring.shop.request.CategoryRequest;
import com.spring.shop.service.BrandService;
import com.spring.shop.service.CategoryService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/brand")
public class BrandRest {
    @Autowired
    BrandService service;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> getAllByProductName(@PathVariable("name") String name){
        return ResponseEntity.ok(service.getAllbyName(name));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.getById(id));
    }
    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody BrandRequest request, BindingResult result){
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.badRequest().body(list);
        }
        return ResponseEntity.ok(service.add(request));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer Id,@Valid @RequestBody BrandRequest request, BindingResult result){
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.badRequest().body(list);
        }
        return ResponseEntity.ok(service.update(Id,request));
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer Id){
        return ResponseEntity.ok(service.delete(Id));
    }
    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restore(@PathVariable("id") Integer Id){
        return ResponseEntity.ok(service.restore(Id));
    }
    @PutMapping("/deletefake/{id}")
    public ResponseEntity<?> deleteFake(@PathVariable("id") Integer Id){
        return ResponseEntity.ok(service.deleteFake(Id));
    }
    @GetMapping("/stopworking")
    public ResponseEntity<?> findEmployeeInActive(){
        return ResponseEntity.ok(service.getInActive());
    }
}
