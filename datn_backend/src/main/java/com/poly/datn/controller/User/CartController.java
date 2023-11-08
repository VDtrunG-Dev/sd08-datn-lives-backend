package com.poly.datn.controller.User;

import com.poly.datn.dto.ProductVariantDTO;
import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TUser;
import com.poly.datn.service.ICartServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private ICartServices cartServices;

    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> pageAdd(HttpServletRequest request, HttpSession session, @RequestBody ProductVariantDTO productVariantDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("succses",cartServices.addCart(session,productVariantDTO),"")
        );
    }
}
