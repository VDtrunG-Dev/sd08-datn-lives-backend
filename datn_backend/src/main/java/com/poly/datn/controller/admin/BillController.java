package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TBill;
import com.poly.datn.model.TRole;
import com.poly.datn.service.impl.BillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/bill/")
public class BillController {
    @Autowired
    private BillServiceImpl billService;

    @GetMapping("view")
    public List<TBill> getAllBills() {
        return billService.getAllBills();
    }
    @GetMapping("find/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<TBill> foundBill = billService.getRoleById(id);
        if (foundBill.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Truy vấn vai trò thành công!", foundBill)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Không tìm thấy vai trò với ID = " + id, "")
            );
        }
    }
    @GetMapping("get-all")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0", name = "page") Integer page){
        return  ResponseEntity.ok(billService.getAll(page));
    }
    @PutMapping("update/{id}")
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
    @GetMapping("get-active-bills")
    public ResponseEntity<?> getActiveBills(@RequestParam(defaultValue = "0", name = "page") Integer page) {
        Page<TBill> activeBills = billService.getActiveBills(1, page);
        return ResponseEntity.ok(activeBills);
    }
    @GetMapping("get-inactive-bills")
    public ResponseEntity<?> getInactiveBills(@RequestParam(defaultValue = "0", name = "page") Integer page) {
        Page<TBill> inactiveBills = billService.getInactiveBills(0, page); // 0 là trạng thái không hoạt động
        return ResponseEntity.ok(inactiveBills);
    }

}
