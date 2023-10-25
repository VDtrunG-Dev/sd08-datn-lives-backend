package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TBill;
import com.poly.datn.service.impl.BillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/bill")
public class BillController {
    @Autowired
    private BillServiceImpl billService;

    @GetMapping("/view")
    public List<TBill> getAllBills() {
        return billService.getAllBills();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateBillStatus(@PathVariable Long id, @RequestParam("status") Integer status) {
        TBill updatedBill = billService.updateBillStatus(id, status);
        if (updatedBill != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Cập nhật trạng thái hóa đơn thành công", updatedBill)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tìm thấy hóa đơn để cập nhật hoặc cập nhật thất bại", "")
            );
        }
    }
}
