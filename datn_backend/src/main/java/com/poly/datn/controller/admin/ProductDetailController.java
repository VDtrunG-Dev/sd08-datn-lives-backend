package com.poly.datn.controller.admin;

import com.poly.datn.dto.ProductDTO;
import com.poly.datn.dto.ProductVariantDTO;
import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TProductVariation;
import com.poly.datn.service.IProductDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productdetail")
@CrossOrigin("/*")
public class ProductDetailController {

    @Autowired
    private IProductDetailServices productDetailServices;

    @GetMapping("")
    private ResponseEntity<?> pageFindAll(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Thành Công",productDetailServices.findAll())
        );
    }

    @PostMapping("/add")
    private ResponseEntity<?> pageAdd(@RequestBody ProductDTO productDTO){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("succes", productDetailServices.addProductDetail(productDTO),productDTO)
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

    @PostMapping("/findByName")
    private ResponseEntity<?> pageFindByName(@RequestBody ProductVariantDTO productVariantDTO){
        return ResponseEntity.ok( productDetailServices.findByName(productVariantDTO));
    }
}
