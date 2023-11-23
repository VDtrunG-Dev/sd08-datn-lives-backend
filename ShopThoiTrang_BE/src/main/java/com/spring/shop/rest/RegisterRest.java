package com.spring.shop.rest;

import com.spring.shop.request.RegisterForm;
import com.spring.shop.service.CustomerService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/register")
public class RegisterRest {
    @Autowired
    CustomerService service;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody RegisterForm form, BindingResult result){
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.badRequest().body(list);
        }
        return ResponseEntity.ok(service.register(form));
    }

}
