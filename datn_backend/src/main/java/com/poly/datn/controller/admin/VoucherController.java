package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/admin/voucher")
public class VoucherController {

    @Autowired
    private VoucherServiceImpl voucherService;


    //hiển thị list voucher
    @GetMapping("/read")
    public List<TVoucher> getALl() {
        return voucherService.getAllVoucher();
    }

    // phân trang
    @GetMapping("/paged")
    public ResponseEntity<ResponseObject> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        int tongVoucher = voucherService.getAllVoucher().size();
        int soTrang = tongVoucher / size;
        if (page > soTrang) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                    "ok", "Không có dữ liệu", ""));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                "ok", "Phân trang thành công.", voucherService.getAllPaged(page, size)));

    }


    // xóa theo id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteVoucher(@PathVariable Long id) {

        try {
            voucherService.deleteVoucher(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Xóa thành công ", "")
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Xóa thất bại", "Không thể tìm thấy voucher để xóa", "")
            );

        }

    }


    // thêm voucher

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addVoucher(@RequestBody TVoucher voucher) {
        TVoucher createdVoucher = voucherService.addVoucher(voucher);

        if (createdVoucher != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Thêm thành công", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("Thất bại", "Không thể thêm", voucherService.addVoucher(voucher))
            );
        }
    }


    // cập nhật voucher
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateVoucher(@PathVariable Long id, @RequestBody TVoucher voucher) {
        TVoucher updatedVoucher = voucherService.updateVoucher(id, voucher);

        if (updatedVoucher != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Cập nhật thành công", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Thất bại", "Không thể tìm thấy voucher  có id  = " + id, "")
            );
        }
    }


    // tìm kiếm theo id
    @GetMapping("/search/{id}")
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


    // getALl by status = 1 kèm phân trang

    @GetMapping("/byStatus/1/paged")
    public ResponseEntity<Page<TVoucher>> getAllByStatus1Paged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<TVoucher> vouchers = voucherService.getAllByStatusPaged(1, page, size);
        if (vouchers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(vouchers);
    }


    // xóa ảo

    @PutMapping("/isDeleted/{id}")
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
                    new ResponseObject("Thất bại", "Không tồn tại voucher này", null)
            );
        }
    }

    @GetMapping("/search")
    public List<TVoucher> searchVouchers(
            @RequestParam(value = "voucherCode", required = false) String voucherCode,
            @RequestParam(value = "quantity", required = false) Integer quantity,
            @RequestParam(value = "maximumCostReduction", required = false) BigDecimal maximumCostReduction) {
        return voucherService.searchAll(voucherCode, quantity, maximumCostReduction);
    }

    // Endpoint for searching vouchers by keyword
    @GetMapping("/searchByKeyword")
    public List<TVoucher> searchVouchersByKeyword(@RequestParam("keyword") String keyword) {
        return voucherService.searchByKeyword(keyword);
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
                        new ResponseObject("Thất bại", "Voucher này chưa bị xóa", null)
                );
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Thất bại", "Không tồn tại voucher này", null)
            );
        }


    }


}
