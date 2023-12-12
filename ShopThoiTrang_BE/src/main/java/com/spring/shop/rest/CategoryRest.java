package com.spring.shop.rest;

import com.spring.shop.entity.Category;
import com.spring.shop.request.CategoryRequest;
import com.spring.shop.service.CategoryService;
import com.spring.shop.service.ProductDetailService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/category")
public class CategoryRest {
    @Autowired
    CategoryService service;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/stopworking")
    public ResponseEntity<?> findCategoryStopƯorking(){
        return ResponseEntity.ok(service.getStopWorking());
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
    public ResponseEntity<?> add(@Valid @RequestBody CategoryRequest category, BindingResult result){
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.badRequest().body(list);
        }
        return ResponseEntity.ok(service.add(category));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer Id,@Valid @RequestBody CategoryRequest category, BindingResult result){
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.badRequest().body(list);
        }
        return ResponseEntity.ok(service.update(Id,category));
    }
    @PutMapping("/deletefake/{id}")
    public ResponseEntity<?> deleteFake(@PathVariable("id") Integer Id){
        return ResponseEntity.ok(service.deleteFake(Id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer Id){
        service.delete(Id);
        return ResponseEntity.ok("Thành Công");
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restore(@PathVariable("id") Integer Id){
        return ResponseEntity.ok(service.restore(Id));
    }
}
