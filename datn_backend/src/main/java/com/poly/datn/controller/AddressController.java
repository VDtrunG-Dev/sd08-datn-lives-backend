package com.poly.datn.controller;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TAddress;
import com.poly.datn.service.impl.AddressServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressServicesImpl addressServices = new AddressServicesImpl();


    @GetMapping("")
    private ResponseEntity<?> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok", "Thành công",addressServices.findAll()));
    }

    @PostMapping("/add")
    private ResponseEntity<?> pageAdd(@RequestBody TAddress address){
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok", addressServices.addAddress(address),""));

    }
}
