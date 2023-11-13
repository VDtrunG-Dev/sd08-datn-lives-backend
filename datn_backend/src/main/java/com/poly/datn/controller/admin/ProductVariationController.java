package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TProductVariation;
import com.poly.datn.service.impl.ProductVariationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/product-variation/")

public class ProductVariationController {
    @Autowired

    private ProductVariationServiceImpl productVariationService;

    @GetMapping("view")
    public ResponseEntity<ResponseObject> getAllProductVariations() {
        List<TProductVariation> allProductVariations = productVariationService.getAllProductVariations();
        if (!allProductVariations.isEmpty()) {
            return ResponseEntity.ok(new ResponseObject("ok", "Danh sách không phân trang", allProductVariations));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Không có dữ liệu", null));
        }
    }

    @GetMapping("get-all-status")
    public ResponseEntity<ResponseObject> getAllStatus(@RequestParam(defaultValue = "0", name = "page") Integer page) {
        Page<TProductVariation> productVariationsPage = productVariationService.getAllStatus(page);
        if (productVariationsPage != null && !productVariationsPage.isEmpty()) {
            return ResponseEntity.ok(new ResponseObject("ok", "Phân trang thành công", productVariationsPage));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Không có dữ liệu", null));
        }
    }

    @GetMapping("get-active-product-variations")
    public ResponseEntity<ResponseObject> getActiveProductVariations(@RequestParam(defaultValue = "0", name = "page") Integer page) {
        Page<TProductVariation> activeProductVariations = productVariationService.getActiveProductVariations(1, PageRequest.of(page, 10));
        if (!activeProductVariations.isEmpty()) {
            return ResponseEntity.ok(new ResponseObject("ok", "Danh sách biến thể sản phẩm hoạt động", activeProductVariations));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Không có dữ liệu biến thể sản phẩm hoạt động", null));
        }
    }

    @GetMapping("find/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<TProductVariation> foundProductVariation = productVariationService.getProductVariationById(id);
        if (foundProductVariation.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Truy vấn biến thể sản phẩm thành công!", foundProductVariation)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Không tìm thấy biến thể sản phẩm với ID = " + id, "")
            );
        }
    }

    @PostMapping("add")
    public ResponseEntity<ResponseObject> createProductVariation(@RequestBody TProductVariation productVariation) {
        TProductVariation createdProductVariation = productVariationService.createProductVariation(productVariation);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("ok", "Tạo biến thể sản phẩm thành công", createdProductVariation)
        );
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseObject> updateProductVariation(@RequestBody TProductVariation newProductVariation, @PathVariable Long id) {
        TProductVariation updatedProductVariation = productVariationService.updateProductVariation(id, newProductVariation);
        if (updatedProductVariation != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Cập nhật biến thể sản phẩm thành công", updatedProductVariation)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tìm thấy biến thể sản phẩm để cập nhật hoặc cập nhật thất bại", "")
            );
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseObject> deleteProductVariation(@PathVariable Long id) {
        productVariationService.deleteProductVariation(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Xóa biến thể sản phẩm thành công", "")
        );
    }

    @GetMapping("search-all")
    public ResponseEntity<ResponseObject> searchAll(
            @RequestParam String name,
            @RequestParam String sku,
            @RequestParam String description
    ) {
        List<TProductVariation> result = productVariationService.searchAll(name, sku, description);
        if (!result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Tìm kiếm thành công", result)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tìm thấy kết quả", null)
            );
        }
    }

    @GetMapping("search-by-keyword")
    public ResponseEntity<ResponseObject> searchByKeyword(@RequestParam String keyword) {
        List<TProductVariation> result = productVariationService.searchByKeyword(keyword);
        if (!result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Tìm kiếm thành công", result)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tìm thấy kết quả", null)
            );
        }
    }

    @PutMapping("delete-fake/{id}")
    public ResponseEntity<ResponseObject> deleteFake(@PathVariable Long id) {
        Optional<TProductVariation> productVariationOptional = productVariationService.getProductVariationById(id);
        if (productVariationOptional.isPresent()) {
            TProductVariation productVariation = productVariationOptional.get();
            productVariation.setStatus(0);

            TProductVariation deletedProductVariation = productVariationService.updateProductVariation(id, productVariation);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Xóa giả mạo thành công", deletedProductVariation)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tồn tại biến thể sản phẩm này", "")
            );
        }
    }

    @PutMapping("return-data/{id}")
    public ResponseEntity<ResponseObject> returnData(@PathVariable Long id) {
        Optional<TProductVariation> productVariationOptional = productVariationService.getProductVariationById(id);
        if (productVariationOptional.isPresent()) {
            TProductVariation productVariation = productVariationOptional.get();
            productVariation.setStatus(1);

            TProductVariation restoredProductVariation = productVariationService.updateProductVariation(id, productVariation);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Khôi phục thành công", restoredProductVariation)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Khôi phục thất bại", "")
            );
        }
    }

}
