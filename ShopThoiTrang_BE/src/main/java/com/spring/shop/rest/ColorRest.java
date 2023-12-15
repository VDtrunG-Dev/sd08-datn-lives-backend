package com.spring.shop.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.shop.request.ColorRequest;
import com.spring.shop.service.ColorService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/color")
public class ColorRest {
    @Autowired
    ColorService service;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getColorById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.getColorByProduct(id));
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
    public ResponseEntity<?> add(@Valid @RequestBody ColorRequest request, BindingResult result){
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.badRequest().body(list);
        }
        return ResponseEntity.ok(service.add(request));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer Id,@Valid @RequestBody ColorRequest request, BindingResult result){
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.badRequest().body(list);
        }
        return ResponseEntity.ok(service.update(Id,request));
    }
    @PutMapping("/deletefake/{id}")
    public ResponseEntity<?> deleteFake(@PathVariable("id") Integer Id){
        return ResponseEntity.ok(service.deleteFake(Id));
    }
    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restore(@PathVariable("id") Integer Id){
        return ResponseEntity.ok(service.restore(Id));
    }
    @GetMapping("/stopworking")
    public ResponseEntity<?> findColorStopWorking(){
        return ResponseEntity.ok(service.getStopWorking());
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer Id){
        return ResponseEntity.ok(service.delete(Id));
    }

}
