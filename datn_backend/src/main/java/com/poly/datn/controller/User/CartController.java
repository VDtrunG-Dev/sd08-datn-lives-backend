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
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private ICartServices cartServices;

    @GetMapping("/findcart")
    private ResponseEntity<?> pageFind(HttpSession session){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("succses","Thành Công",cartServices.findCart(session))
        );
    }
    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> pageAdd(HttpSession session, @RequestBody ProductVariantDTO productVariantDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("succses",cartServices.addCart(session,productVariantDTO),"")
        );
    }


//    @PutMapping("/update")
//    public ResponseEntity<?> pageUpdate(@RequestBody )

}
