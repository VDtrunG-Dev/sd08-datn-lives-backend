package com.spring.shop.rest;

import com.spring.shop.entity.OperationHistory;
import com.spring.shop.service.OperationHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/operationhistory")
public class OperationHistoryRest {
    @Autowired
    OperationHistoryService service;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @PostMapping
    public ResponseEntity<?> add(@RequestBody OperationHistory operationHistory){
        return ResponseEntity.ok(service.add(operationHistory));
    }

}
