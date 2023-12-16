package com.spring.shop.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.spring.shop.request.SizeRequest;
import com.spring.shop.service.SizeService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/size")
public class SizeRest {
    @Autowired
    SizeService service;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/stopworking")
    public ResponseEntity<?> findSizeStopWorking(){
        return ResponseEntity.ok(service.getStopWorking());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> getAllBySizeName(@PathVariable("name") String name){
        return ResponseEntity.ok(service.getAllbyName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody SizeRequest request, BindingResult result){
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.badRequest().body(list);
        }
        return ResponseEntity.ok(service.add(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer Id,@Valid @RequestBody SizeRequest request, BindingResult result){
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
    @PutMapping("/deletefake/{id}")
    public ResponseEntity<?> deleteFake(@PathVariable("id") Integer Id){
        return ResponseEntity.ok(service.deleteFake(Id));
    }
    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restore(@PathVariable("id") Integer Id){
        return ResponseEntity.ok(service.restore(Id));
    }
}
