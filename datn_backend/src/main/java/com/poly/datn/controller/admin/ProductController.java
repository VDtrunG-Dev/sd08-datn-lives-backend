package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TProduct;
import com.poly.datn.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;


    @GetMapping("")
    public ResponseEntity<?> getAllProduct(@RequestParam(name = "page",defaultValue = "1") int page) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công",productService.getAllProducts(page))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<TProduct> foundProduct = productService.getProductById(id);
        if (foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query product successfully!", foundProduct)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Cannot find product with id = " + id, "")
            );
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> insertProduct(@RequestBody TProduct product) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", productService.createProduct(product),product )
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@RequestBody TProduct product, @PathVariable Long id) {
            product.setId(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", productService.updateProduct(id,product), product)
            );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Xoá Sản Phẩm Thành Công", "")
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Xoá Sản Phẩm Thất Bại", "")
            );
        }
    }

    @PostMapping("/search")
    public ResponseEntity<ResponseObject> updateProduct(@RequestParam(name = "search", required = false) String search) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công", productService.findByKeyword(search))
        );
    }

    @PostMapping("/active/{id}")
    public ResponseEntity<ResponseObject> acctiveProduct(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công", productService.activeProduct(id))
        );
    }
}
