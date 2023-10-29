package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TProduct;
import com.poly.datn.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/admin/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;


    @GetMapping("")
    public ResponseEntity<?> getAllProduct(@PathVariable(name = "page") int page) {
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

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertProduct(@RequestBody TProduct product) {
        // Các logic kiểm tra và xử lý nên thực hiện trong ProductService
        // ProductService sẽ xử lý việc kiểm tra trùng tên sản phẩm và thêm sản phẩm mới.
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert product successfully", productService.createProduct(product))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@RequestBody TProduct newProduct, @PathVariable Long id) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Can't find product to update4", "")
            );


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        // Các logic kiểm tra và xử lý nên thực hiện trong ProductService
        // ProductService sẽ xử lý việc xóa sản phẩm.
        try {
            productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete product successfully", "")
            );
        } catch (IllegalArgumentException e) {
            // Xử lý lỗi nếu sản phẩm không tồn tại hoặc không thể xóa.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Can't find product to delete", "")
            );
        }
    }
}
