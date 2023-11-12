package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TBill;
import com.poly.datn.service.impl.BillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/bill/")
public class BillController {
    @Autowired
    private BillServiceImpl billService;

    @GetMapping("view")
    public ResponseEntity<ResponseObject> getAllBills() {
        List<TBill> result = billService.getAllBills();
        if (!result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách hóa đơn thành công", result)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không có dữ liệu hóa đơn", null)
            );
        }
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
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                "OK", "Phân trang thành công", billService.getAll(page)
        ));
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
    public ResponseEntity<ResponseObject> getActiveBills(@RequestParam(defaultValue = "0", name = "page") Integer page) {
        Page<TBill> activeBills = billService.getActiveBills(1, page);
        if (activeBills != null && activeBills.hasContent()) {
            return ResponseEntity.ok(new ResponseObject("ok", "Danh sách hóa đơn hoạt động", activeBills));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không có dữ liệu hóa đơn hoạt động", null)
            );
        }
    }

    @GetMapping("get-inactive-bills")
    public ResponseEntity<ResponseObject> getInactiveBills(@RequestParam(defaultValue = "0", name = "page") Integer page) {
        Page<TBill> inactiveBills = billService.getInactiveBills(0, page); // 0 là trạng thái không hoạt động
        if (inactiveBills != null && inactiveBills.hasContent()) {
            return ResponseEntity.ok(new ResponseObject("ok", "Danh sách hóa đơn không hoạt động", inactiveBills));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không có dữ liệu hóa đơn không hoạt động", null)
            );
        }
    }

    @GetMapping("search-all/")
    public ResponseEntity<ResponseObject> searchAll(
            @RequestParam String customer,
            @RequestParam String shippingMethod,
            @RequestParam BigDecimal cash
    ) {
        List<TBill> result = billService.searchAll(customer, shippingMethod, cash);
        if (!result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Tìm kiếm hóa đơn thành công", result)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tìm thấy kết quả tìm kiếm", null)
            );
        }
    }

    @GetMapping("search-by-keyword/")
    public ResponseEntity<ResponseObject> searchByKeyword(@RequestParam String keyword) {
        List<TBill> result = billService.searchByKeyword(keyword);
        if (!result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Tìm kiếm hóa đơn theo từ khóa thành công", result)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tìm thấy kết quả tìm kiếm theo từ khóa", null)
            );
        }
    }
}
