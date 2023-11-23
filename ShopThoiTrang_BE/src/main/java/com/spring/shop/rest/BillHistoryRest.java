package com.spring.shop.rest;

import com.spring.shop.request.BillHistoryRequest;
import com.spring.shop.service.BillHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/billhistory")
public class BillHistoryRest {
    @Autowired
    BillHistoryService billHistoryService;
    @PostMapping
    public ResponseEntity<?> add(@RequestBody BillHistoryRequest request){
        return ResponseEntity.ok(billHistoryService.add(request));
    }
    @GetMapping("/{code}")
    public ResponseEntity<?> getallbybill(@PathVariable("code") String code){
        return ResponseEntity.ok(billHistoryService.getAllByBill(code));
    }
    @DeleteMapping("/deletebillhistory/{code}")
    public ResponseEntity<?> deletebillhistory(@PathVariable("code") String code){
        billHistoryService.deleteBillDetailByCode(code);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
