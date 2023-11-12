package com.poly.datn.controller;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TAddress;
import com.poly.datn.service.impl.AddressServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressServicesImpl addressServices ;


    @GetMapping("")
    private ResponseEntity<?> findAll(@RequestParam(name = "page",defaultValue = "1") int pageNumber,
                                      @RequestParam(name = "search", defaultValue = "") String search){
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok", "Thành công",addressServices.findAll(pageNumber,search)));
    }

    @PostMapping("/add")
    private ResponseEntity<?> pageAdd(@RequestBody TAddress address ){
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok", addressServices.addAddress(address),address));

    }


    @PutMapping("/update/{id}")
    private ResponseEntity<?> pageUpdate(@RequestBody TAddress address,@PathVariable Long id){
        address.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok", addressServices.updateAddress(address),address));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> pageDelete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok", addressServices.deleteAddressById(id),""));
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> pageFindById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok","" ,addressServices.findById(id)));
    }

    @GetMapping("/search")
    private ResponseEntity<?> findSearch(@RequestParam(name = "search",required = false) String search){
        return ResponseEntity.status(HttpStatus.OK).body(new
                ResponseObject("ok", "Thành công",addressServices.search(search)));
    }

}
