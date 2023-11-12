package com.poly.datn.controller.admin;

import com.poly.datn.dto.ProductDTO;
import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TProduct;
import com.poly.datn.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/products")
@CrossOrigin("http://localhost:5173")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;


    @GetMapping("")
    public ResponseEntity<?> getAllProduct(@RequestParam(name = "page",defaultValue = "0") int page,
                                           @RequestParam(name = "search",defaultValue = "") String search) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công",productService.getProducts(page,search))
        );
    }

    @GetMapping("/listNameProduct")
    public ResponseEntity<?> pgaeNameProduct(@RequestParam(name = "search",defaultValue = "") String search){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công",productService.displayNameProduct(search))
        );
    }

    @GetMapping("/findall")
    public ResponseEntity<?> pageFindAll(@RequestParam(name = "page",defaultValue = "0") int page,
                                           @RequestParam(name = "search",defaultValue = "") String search) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công",productService.findAll(page,search))
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<TProduct> foundProduct = productService.getProductById(id);
        if (foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Thành Công", foundProduct)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Không tìm thấy sản phẩm = " + id, "")
            );
        }
    }

    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObject> insertProduct(@RequestBody ProductDTO productName) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", productService.createProduct(productName.getProductName()),productName )
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

    @PutMapping("/active/{id}")
    public ResponseEntity<ResponseObject> acctiveProduct(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công", productService.activeProduct(id))
        );
    }
}
