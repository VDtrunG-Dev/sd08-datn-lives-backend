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
    private ResponseEntity<?> findAll(@RequestParam(name = "page") int pageNumber){
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok", "Thành công",addressServices.findAll(pageNumber)));
    }

    @PostMapping("/add")
    private ResponseEntity<?> pageAdd(@RequestBody TAddress address ){
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok", addressServices.addAddress(address),""));

    }


    @PutMapping("/update/{id}")
    private ResponseEntity<?> pageUpdate(@RequestBody TAddress address,@PathVariable Long id){
        address.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok", addressServices.updateAddress(address),""));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> pageDelete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok", addressServices.deleteAddressById(id),""));
    }
}
