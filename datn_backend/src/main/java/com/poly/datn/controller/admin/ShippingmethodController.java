package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.dto.ShippingMethodRequest;
import com.poly.datn.model.TShippingMethod;
import com.poly.datn.service.ShippingmethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/shippingmethod")
public class ShippingmethodController {

    @Autowired
    private ShippingmethodService shippingmethodService;

    @GetMapping()
//    public List<TShippingMethod> getAllActive() {
//        return shippingmethodService.getAll();
//    }
    public List<TShippingMethod> getAllActive() {
        return shippingmethodService.getAllActice();
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseObject> getPage(@RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo
            , @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Pagination Successfully"
                        , shippingmethodService.PageGetAllTShippingMethods(pageNo,pageSize))
        );
    }

    //    C1
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<TShippingMethod> foundSmethod = shippingmethodService.getTShippingMethodById(id);
        if (foundSmethod.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query successfully!", foundSmethod)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Cannot find any with id = " + id, "")
            );
        }
    }

    @PostMapping("/addnew")
    public ResponseEntity<ResponseObject> insertProduct(@RequestBody ShippingMethodRequest request) {
        // Các logic kiểm tra và xử lý nên thực hiện trong ProductService
        // ProductService sẽ xử lý việc kiểm tra trùng tên sản phẩm và thêm sản phẩm mới.
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Create new successfully", shippingmethodService.createTShippingMethod(request))
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@RequestBody ShippingMethodRequest request, @PathVariable Long id) {
        // Các logic kiểm tra và xử lý nên thực hiện trong ProductService
        // ProductService sẽ xử lý việc cập nhật sản phẩm.
        try {
            TShippingMethod paymentTypeUpdate = shippingmethodService.updateTShippingMethod(id, request);
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
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        // Các logic kiểm tra và xử lý nên thực hiện trong ProductService
        // ProductService sẽ xử lý việc xóa sản phẩm.
        try {
            shippingmethodService.deleteTShippingMethod(id);
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
}
