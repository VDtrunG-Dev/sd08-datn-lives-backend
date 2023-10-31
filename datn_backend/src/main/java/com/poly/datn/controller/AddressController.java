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
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressServicesImpl addressServices = new AddressServicesImpl();

    @GetMapping("")
    private ResponseEntity<?> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok", "Thành công",addressServices.findAll()));
    }

    @GetMapping("/findall")
    private ResponseEntity<?> findAllPage(@RequestParam(name = "page") int pageNumber){
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok", "Thành công",addressServices.findAllPage(pageNumber)));
    }

    @PostMapping("/add")
    private ResponseEntity<?> pageAdd(@RequestBody TAddress address ){
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok", addressServices.addAddress(address),""));

    }

    @GetMapping("/findById/{id}")
    private ResponseEntity<?> pageFindById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok","Thành Công",addressServices.findById(id)));
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

    @PostMapping("/findByKeyWord")
    private ResponseEntity<?> pageFindBYKeyWord(@RequestBody String keyword){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Tìm Thành Công",addressServices.findByKeywork(keyword))
        );
    }

    @GetMapping("/findbystatus0")
    private ResponseEntity<?> pageFindStatus(){
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok", "Thành Công",addressServices.findByStatus0()));
    }
}
