package com.spring.shop.rest;

import com.spring.shop.request.CapNhatProfile;
import com.spring.shop.request.EmployeeRequest;
import com.spring.shop.request.ForgetForm;
import com.spring.shop.service.EmployeeService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeRest {

    @Autowired
    EmployeeService service;

    @GetMapping("/stopworking")
    public ResponseEntity<?> findEmployeeInActive(){
        return ResponseEntity.ok(service.getInActive());
    }
    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> getAllByEmployee(@PathVariable("name") String name){
        return ResponseEntity.ok(service.getAllbyName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody EmployeeRequest request, BindingResult result){
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.badRequest().body(list);
        }
        return ResponseEntity.ok(service.add(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer Id, @Valid @RequestBody EmployeeRequest request, BindingResult result){
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
    @GetMapping("/getByUsername/{usernmae}")
    public ResponseEntity<?> getByUsername(@PathVariable("usernmae") String name){
        return ResponseEntity.ok(service.getByUsername(name));
    }
    @PutMapping("/forget")
    public ResponseEntity<?> forget(@Valid @RequestBody ForgetForm form, BindingResult result){
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.badRequest().body(list);
        }
        if(!form.getEmail().equals(service.getByUsername(form.getUsername()).getEmail())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("err");
        }
        return ResponseEntity.ok(service.forget(form));
    }
    @PutMapping("/updateprofile/{id}")
    public ResponseEntity<?> updateprofile(@PathVariable("id") Integer id, @Valid @RequestBody CapNhatProfile form, BindingResult result){
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.badRequest().body(list);
        }
        return ResponseEntity.ok(service.updateprofile(id,form));
    }



    //////
    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restore(@PathVariable("id") Integer Id){
        return ResponseEntity.ok(service.restore(Id));
    }
    @PutMapping("/deletefake/{id}")
    public ResponseEntity<?> deleteFake(@PathVariable("id") Integer Id){
        return ResponseEntity.ok(service.deleteFake(Id));
    }
}
