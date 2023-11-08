package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TVariantValue;
import com.poly.datn.service.impl.VariantValueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/variant-value/")
public class VariantValueController {
    @Autowired
    private VariantValueServiceImpl variantValueService;

    @GetMapping("view")
    public ResponseEntity<ResponseObject> getAllVariantValues() {
        List<TVariantValue> allVariantValues = variantValueService.getAllVariantValues();
        if (!allVariantValues.isEmpty()) {
            return ResponseEntity.ok(new ResponseObject("ok", "Danh sách không phân trang", allVariantValues));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Không có dữ liệu", null));
        }
    }

    @GetMapping("get-all-status")
    public ResponseEntity<?> getAllStatus(@RequestParam(defaultValue = "0", name = "page") Integer page) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                "OK", "Phân trang thành công", variantValueService.getAllStatus(page)
        ));
    }

    @GetMapping("find/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<TVariantValue> foundVariantValue = variantValueService.getVariantValueById(id);
        if (foundVariantValue.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Truy vấn giá trị biến thể thành công!", foundVariantValue)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Không tìm thấy giá trị biến thể với ID = " + id, "")
            );
        }
    }

    @PostMapping("add")
    public ResponseEntity<ResponseObject> createVariantValue(@RequestBody TVariantValue variantValue) {
        TVariantValue createdVariantValue = variantValueService.createVariantValue(variantValue);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("ok", "Tạo giá trị biến thể thành công", createdVariantValue)
        );
    }

    @GetMapping("get-inactive-variant-values")
    public ResponseEntity<ResponseObject> getInActiveVariantValues(@RequestParam(defaultValue = "0", name = "page") Integer page) {
        Page<TVariantValue> inActiveVariantValues = variantValueService.getInActiveVariantValues(0, PageRequest.of(page, 5));
        if (!inActiveVariantValues.isEmpty()) {
            return ResponseEntity.ok(new ResponseObject("ok", "Danh sách giá trị biến thể không hoạt động", inActiveVariantValues));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Không có dữ liệu giá trị biến thể không hoạt động", null));
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseObject> updateVariantValue(@RequestBody TVariantValue newVariantValue, @PathVariable Long id) {
        TVariantValue updatedVariantValue = variantValueService.updateVariantValue(id, newVariantValue);
        if (updatedVariantValue != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Cập nhật giá trị biến thể thành công", updatedVariantValue)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tìm thấy giá trị biến thể để cập nhật hoặc cập nhật thất bại", "")
            );
        }
    }

    @PutMapping("delete-fake/{id}")
    public ResponseEntity<ResponseObject> deleteFake(@PathVariable Long id) {
        Optional<TVariantValue> variantValueOptional = variantValueService.getVariantValueById(id);
        if (variantValueOptional.isPresent()) {
            TVariantValue variantValue = variantValueOptional.get();
            variantValue.setStatus(0);

            TVariantValue delete = variantValueService.updateVariantValue(id, variantValue);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Xóa thành công", delete)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tồn tại giá trị biến thể này", "")
            );
        }
    }

    @PutMapping("return-data/{id}")
    public ResponseEntity<ResponseObject> returnData(@PathVariable Long id) {
        Optional<TVariantValue> variantValueOptional = variantValueService.getVariantValueById(id);
        if (variantValueOptional.isPresent()) {
            TVariantValue variantValue = variantValueOptional.get();
            variantValue.setStatus(1);

            TVariantValue delete = variantValueService.updateVariantValue(id, variantValue);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Khôi phục thành công", delete)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Khôi phục thất bại", "")
            );
        }


    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseObject> deleteVariantValue(@PathVariable Long id) {
        variantValueService.deleteVariantValue(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Xóa giá trị biến thể thành công", "")
        );
    }


}
