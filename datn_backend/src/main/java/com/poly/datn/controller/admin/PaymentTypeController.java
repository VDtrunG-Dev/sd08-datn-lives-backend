package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.dto.PaymentTypeRequest;
import com.poly.datn.model.TPaymentType;
import com.poly.datn.service.PaymentTypeService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/paymenttype")
public class PaymentTypeController {
    @Autowired
    private PaymentTypeService paymentTypeService;

    @GetMapping()
    public List<TPaymentType> getAllActive() {
        return paymentTypeService.getAllActice();
    }


    @GetMapping("/deleted")
    public List<TPaymentType> getAllDeleted() {
        return paymentTypeService.getAllDeleted();
    }


    @GetMapping("/page")
    public ResponseEntity<ResponseObject> getPage(@RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo
            , @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Pagination Successfully"
                        , paymentTypeService.PageGetAllPaymenttypes(pageNo, pageSize))
        );
    }

    @GetMapping("/page-deleted")
    public ResponseEntity<ResponseObject> getPageDeleted(@RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo
            , @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Pagination Successfully"
                        , paymentTypeService.PageGetAllPaymenttypesDeleted(pageNo, pageSize))
        );
    }

    //    C1
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<TPaymentType> foundPaymentType = paymentTypeService.getPaymentTypeById(id);
        if (foundPaymentType.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query successfully!", foundPaymentType)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Cannot find any with id = " + id, "")
            );
        }
    }

    @PostMapping("/add-new")
    public ResponseEntity<ResponseObject> insertPaymenttype(@RequestBody PaymentTypeRequest request) {
        // Các logic kiểm tra và xử lý nên thực hiện trong PaymenttypeService
        // PaymenttypeService sẽ xử lý việc kiểm tra trùng tên sản phẩm và thêm sản phẩm mới.
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Create new successfully", paymentTypeService.createPaymentType(request))
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updatePaymenttype(@RequestBody PaymentTypeRequest request, @PathVariable Long id) {
        // Các logic kiểm tra và xử lý nên thực hiện trong PaymenttypeService
        // PaymenttypeService sẽ xử lý việc cập nhật sản phẩm.
        try {
            TPaymentType paymentTypeUpdate = paymentTypeService.updatePaymentType(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update successfully", paymentTypeUpdate)
            );
        } catch (IllegalArgumentException e) {
            // Xử lý lỗi nếu sản phẩm không tồn tại hoặc không thể update.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Can't find any to update4", "")
            );
        }
    }

    @PutMapping("/update-active/{id}")
    public ResponseEntity<ResponseObject> updatePaymenttypeActive(@PathVariable Long id) {
        // Các logic kiểm tra và xử lý nên thực hiện trong PaymenttypeService
        // PaymenttypeService sẽ xử lý việc cập nhật sản phẩm.
        try {
            TPaymentType paymentTypeUpdate = paymentTypeService.updatePaymentTypeActive(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update successfully", paymentTypeUpdate)
            );
        } catch (IllegalArgumentException e) {
            // Xử lý lỗi nếu sản phẩm không tồn tại hoặc không thể update.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Can't find any to update4", "")
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deletePaymenttype(@PathVariable Long id) {
        // Các logic kiểm tra và xử lý nên thực hiện trong PaymenttypeService
        // PaymenttypeService sẽ xử lý việc xóa sản phẩm.
        try {
            paymentTypeService.deletePaymentType(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete successfully", "")
            );
        } catch (IllegalArgumentException e) {
            // Xử lý lỗi nếu sản phẩm không tồn tại hoặc không thể xóa.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Can't find any to delete", "")
            );
        }
    }

    @GetMapping("/search-name")
    public List<TPaymentType> searchAll(@RequestParam(name = "searchInput") String namePayment) {
        return paymentTypeService.searchAll(namePayment);
    }
}
