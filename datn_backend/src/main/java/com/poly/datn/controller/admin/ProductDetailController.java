package com.poly.datn.controller.admin;

import com.poly.datn.dto.ProductDTO;
import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TProductVariation;
import com.poly.datn.service.IProductDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productdetail")
public class ProductDetailController {

    @Autowired
    private IProductDetailServices productDetailServices;

    @PostMapping("/addproductdetails")
    private ResponseEntity<?> pageAddProductDetail(@RequestBody ProductDTO productDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("ok", productDetailServices.addProductDetail(productDTO),productDTO)
        );
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> pageUpdateProduct(@RequestBody TProductVariation productVariation,@PathVariable(name = "id") Long id){
        productVariation.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", productDetailServices.updateProductDetails(productVariation),productVariation)
        );
    }

    @GetMapping("/active/{id}")
    private ResponseEntity<?> pageActiveProduct(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", productDetailServices.activeProductDetail(id),"")
        );
    }

    @GetMapping("/delete/{id}")
    private ResponseEntity<?> pageDeleteProduct(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", productDetailServices.deleteProductDetail(id),"")
        );
    }
}
