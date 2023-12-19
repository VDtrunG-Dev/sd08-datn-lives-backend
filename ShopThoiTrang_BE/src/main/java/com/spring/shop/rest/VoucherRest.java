package com.spring.shop.rest;

import com.spring.shop.request.VoucherRequest;
import com.spring.shop.service.VoucherService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/voucher")
public class VoucherRest {
    @Autowired
    VoucherService service;

    @GetMapping()
    public ResponseEntity<?> getAllActive(){
        return ResponseEntity.ok(service.getAllActive());
    }

    @GetMapping("/prepare")
    public ResponseEntity<?> getAllPrepare(){
        return ResponseEntity.ok(service.getAllPrepare());
    }

    @GetMapping("/stop")
    public ResponseEntity<?> getAllStop(){
        return ResponseEntity.ok(service.getAllStop());
    }
    @GetMapping("/delete")
    public ResponseEntity<?> getAllDelete(){
        return ResponseEntity.ok(service.getAllDelete());
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<?> getAllByVoucher(@PathVariable("name") String name){
        return ResponseEntity.ok(service.getAllbyName(name));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.getById(id));
    }
    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody VoucherRequest request, BindingResult result){
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.badRequest().body(list);
        }
        return ResponseEntity.ok(service.add(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer Id, @Valid @RequestBody VoucherRequest request, BindingResult result){
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

    @GetMapping("/getVoucherTop/{totalPrice}")
    public ResponseEntity<?> getVoucherTop(@PathVariable("totalPrice") String totalPrice){
        return ResponseEntity.ok(service.getVoucherTop(Integer.parseInt(totalPrice)));
    }
}


