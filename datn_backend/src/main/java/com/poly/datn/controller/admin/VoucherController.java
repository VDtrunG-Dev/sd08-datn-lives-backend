package com.poly.datn.controller.admin;

import com.poly.datn.model.TVoucher;
import com.poly.datn.service.impl.VoucherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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


@RestController
@RequestMapping("/api/admin/voucher")
public class VoucherController {

    @Autowired
    private VoucherServiceImpl voucherService;


    @GetMapping("/view")
    public List<TVoucher> getALl() {
        return voucherService.getAllVoucher();
    }


    @GetMapping("/paged")
    public ResponseEntity<Page<TVoucher>> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        return ResponseEntity.ok(voucherService.getAllPaged(page, size));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVoucher(@PathVariable Long id) {
        boolean result = voucherService.deleteVoucher(id);

        if (result) {
            return ResponseEntity.ok("Voucher deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Voucher not found with the provided ID.");
        }
    }


    @PostMapping("/add")
    public ResponseEntity<String> addVoucher(@RequestBody TVoucher voucher) {
        TVoucher createdVoucher = voucherService.addVoucher(voucher);

        if (createdVoucher != null) {
            return ResponseEntity.ok("Voucher added successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add the voucher.");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateVoucher(@PathVariable Long id, @RequestBody TVoucher voucher) {
        TVoucher updatedVoucher = voucherService.updateVoucher(id, voucher);

        if (updatedVoucher != null) {
            return ResponseEntity.ok("Voucher updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Voucher not found with the provided ID.");
        }
    }
}
