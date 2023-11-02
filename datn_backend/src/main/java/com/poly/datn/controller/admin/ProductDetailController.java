package com.poly.datn.controller.admin;

import com.poly.datn.dto.ProductDTO;
import com.poly.datn.dto.ResponseObject;
import com.poly.datn.service.ProductDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productdetail")
public class ProductDetailController {

    @Autowired
    private ProductDetailServices productDetailServices;

    @PostMapping("/addproductdetails")
    private ResponseEntity<?> pageAddProductDetail(@RequestBody ProductDTO productDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("ok", productDetailServices.addProductDetail(productDTO),productDTO)
        );
    }



}
