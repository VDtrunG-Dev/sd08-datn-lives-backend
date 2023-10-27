package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TRank;
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
import java.util.Optional;


@RestController
@RequestMapping("/api/admin/voucher")
public class VoucherController {

    @Autowired
    private VoucherServiceImpl voucherService;


    //hiển thị list voucher
    @GetMapping("/view")
    public List<TVoucher> getALl() {
        return voucherService.getAllVoucher();
    }

   // phân trang
    @GetMapping("/paged")
    public ResponseEntity<Page<TVoucher>> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        return ResponseEntity.ok(voucherService.getAllPaged(page, size));
    }


    // xóa theo id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVoucher(@PathVariable Long id) {
        boolean result = voucherService.deleteVoucher(id);

        if (result) {
            return ResponseEntity.ok("Xoá thành công .");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy voucher với ID được cung cấp.");
        }
    }


    // thêm voucher

    @PostMapping("/add")
    public ResponseEntity<String> addVoucher(@RequestBody TVoucher voucher) {
        TVoucher createdVoucher = voucherService.addVoucher(voucher);

        if (createdVoucher != null) {
            return ResponseEntity.ok("Thêm thành công.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Thêm thất bại.");
        }
    }



    // cập nhật voucher
    @PutMapping("/{id}")
    public ResponseEntity<String> updateVoucher(@PathVariable Long id, @RequestBody TVoucher voucher) {
        TVoucher updatedVoucher = voucherService.updateVoucher(id, voucher);

        if (updatedVoucher != null) {
            return ResponseEntity.ok("Cập nhật thành công.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy voucher với ID được cung cấp.");
        }
    }



    // tìm kiếm theo id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<TVoucher> foundVoucher = voucherService.getVoucherById(id);
        if (foundVoucher.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Tìm kiếm voucher thành công!", foundVoucher)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Không thể tìm thấy voucher có id  = " + id, "")
            );
        }
    }


    // hiển thị tất cả trạng thái
    @GetMapping("/bystatus")
    public ResponseEntity<List<TVoucher>> getAllByStatus() {
        List<TVoucher> vouchers = voucherService.getAllVouchersByStatus(0);
        if (vouchers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(vouchers);
    }


    // xóa ảo

    @PutMapping("deleteFakeVoucher/{id}")
    public ResponseEntity<ResponseObject> deleteFakeVoucher(@PathVariable Long id) {
        Optional<TVoucher> voucherOptional = voucherService.getVoucherById(id);
        if (voucherOptional.isPresent()) {
            TVoucher voucher = voucherOptional.get();
            voucher.setStatus(0);

            TVoucher updatedVoucher = voucherService.updateVoucher(id, voucher);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Đánh dấu xóa voucher thành công", updatedVoucher)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tồn tại voucher này", null)
            );
        }
    }



// khôi phục dữ liệu bị xóa
    @PutMapping("restoreVoucher/{id}")
    public ResponseEntity<ResponseObject> restoreVoucher(@PathVariable Long id) {
        Optional<TVoucher> voucherOptional = voucherService.getVoucherById(id);
        if (voucherOptional.isPresent()) {
            TVoucher voucher = voucherOptional.get();


            if (voucher.getStatus() == 0) {
                voucher.setStatus(1);
                TVoucher restoredVoucher = voucherService.updateVoucher(id, voucher);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Khôi phục voucher thành công", restoredVoucher)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ResponseObject("failed", "Voucher này chưa bị xóa", null)
                );
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tồn tại voucher này", null)
            );
        }
    }


}
