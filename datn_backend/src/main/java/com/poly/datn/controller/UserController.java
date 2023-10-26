package com.poly.datn.controller;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.dto.UserDTO;
import com.poly.datn.repository.IUserRepository;
import com.poly.datn.service.impl.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServicesImpl userServices;

    @Autowired
    private IUserRepository userRepository;

    @GetMapping("/findall")
    private ResponseEntity<?> pageAllUser(@RequestParam(name = "page") int pageNumber){

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công",userServices.findAll(pageNumber))
        );
    }

    @GetMapping("/findbyemail/{email}")
    private ResponseEntity<?> pageFindByEmailUser(@PathVariable(name = "email") String email){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công",userServices.findByEmail(email))
        );
    }


    @PostMapping("/add")
    private ResponseEntity<?> pageAddUser(@RequestBody UserDTO user){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("", userServices.saveUser(user),"")
        );
    }

    @PutMapping("/update")
    private ResponseEntity<?> pageUpdateUser(@RequestBody UserDTO user){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("", userServices.saveUser(user),"")
        );
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> pageDeleteUser(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Delete", userServices.deleteUserById(id),"")
        );
    }

    @PutMapping("/active/{id}")
    private ResponseEntity<?> pageACtiveeUser(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Delete", userServices.active(id),"")
        );
    }
}
