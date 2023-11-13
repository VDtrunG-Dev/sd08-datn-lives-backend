package com.poly.datn.controller;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.dto.UserDTO;
import com.poly.datn.model.TUser;
import com.poly.datn.repository.IUserRepository;
import com.poly.datn.service.impl.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServicesImpl userServices;

    @Autowired
    private IUserRepository userRepository;

    @GetMapping("")
    private ResponseEntity<?> pageAllUser(@RequestParam(name = "page",defaultValue = "1") int pageNumber,
                                          @RequestParam(name = "search",defaultValue = "") String search){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công",userServices.findAll(pageNumber,search))
        );
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> pageFindByEmailUser(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công",userServices.findById(id))
        );
    }


    @PostMapping("/add")
    private ResponseEntity<?> pageAddUser(@RequestBody UserDTO user){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("", userServices.saveUser(user),user)
        );
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> pageUpdateUser(@RequestBody TUser user, @PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("", userServices.updateUser(user),user)
        );
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> pageDeleteUser(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Delete", userServices.deleteUserById(id),"")
        );
    }

    @PutMapping("/active/{id}")
    private ResponseEntity<?> pageSearch(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công",userServices.activeUser(id))
        );
    }
}
