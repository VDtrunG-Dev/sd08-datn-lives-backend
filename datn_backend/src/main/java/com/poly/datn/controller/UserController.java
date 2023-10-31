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

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServicesImpl userServices;

    @Autowired
    private IUserRepository userRepository;

    @GetMapping("/findall")
    private ResponseEntity<?> pageAllUserPage(@RequestParam(name = "page") int pageNumber){

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công",userServices.findAllPage(pageNumber))
        );
    }

    @GetMapping("")
    private ResponseEntity<?> pageAllUser(@RequestParam(name = "page") int pageNumber){

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công",userServices.findAll())
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
                new ResponseObject("", userServices.saveUser(user),user)
        );
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> pageUpdateUser(@RequestBody TUser user, @PathVariable Long id){
        user.setId(id);
        TUser userFindById = userServices.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("", userServices.updateUser(user),userFindById)
        );
    }


    @PutMapping("/active/{id}")
    private ResponseEntity<?> pageActive(@PathVariable Long id){
        TUser user = userServices.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("", userServices.active(id),user)
        );
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> pageDeleteUser(@PathVariable(name = "id") Long id){
        TUser user = userServices.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("", userServices.deleteUserById(id),user)
        );
    }

    @PostMapping("/findbystatus")
    private ResponseEntity<?> findByStatus(@RequestBody int status){
        List<TUser> user = userServices.findByStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Thành Công",user)
        );
    }

    @PostMapping("findBykeyword")
    private ResponseEntity<?> pageFindByKeyword(@RequestBody String keyword){
        List<TUser> users = userServices.findByKeyword(keyword);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Thành Công",users)
        );
    }
}
