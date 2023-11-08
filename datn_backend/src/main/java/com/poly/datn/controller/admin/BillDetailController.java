package com.poly.datn.controller.admin;

import com.poly.datn.dto.BillDetailRequest;
import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TBillDetail;
import com.poly.datn.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@CrossOrigin("*")
@RequestMapping("/api/billdatail")
public class BillDetailController {
    @Autowired
    private BillDetailService billDetailService;
    @GetMapping()
//    public List<TBillDetail> getAllActive() {
//        return billDetailService.getAll();
//    }
    public List<TBillDetail> getAllActive() {
        return billDetailService.getAllActice();
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseObject> getPage(@RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo
            , @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Pagination Successfully"
                        , billDetailService.PageGetAllBillDetails(pageNo, pageSize))
        );
    }

    //    C1
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<TBillDetail> foundBillDetail = billDetailService.getBillDetailById(id);
        if (foundBillDetail.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query successfully!", foundBillDetail)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Cannot find any with id = " + id, "")
            );
        }
    }

    @PostMapping("/addnew")
    public ResponseEntity<ResponseObject> insertBillDetail(@RequestBody BillDetailRequest request) {
        // Các logic kiểm tra và xử lý nên thực hiện trong BillDetailService
        // BillDetailService sẽ xử lý việc kiểm tra trùng tên sản phẩm và thêm sản phẩm mới.
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Create new successfully", billDetailService.createBillDetail(request))
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateBillDetail(@RequestBody BillDetailRequest request, @PathVariable Long id) {
        // Các logic kiểm tra và xử lý nên thực hiện trong BillDetailService
        // BillDetailService sẽ xử lý việc cập nhật sản phẩm.
        try {
            TBillDetail paymentTypeUpdate = billDetailService.updateBillDetail(id, request);
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
    public ResponseEntity<ResponseObject> deleteBillDetail(@PathVariable Long id) {
        // Các logic kiểm tra và xử lý nên thực hiện trong BillDetailService
        // BillDetailService sẽ xử lý việc xóa sản phẩm.
        try {
            billDetailService.deleteBillDetail(id);
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

    @GetMapping("/da-thanh-toan")
    public ResponseEntity<ResponseObject> daThanhToanPage(@RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo
            , @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize){
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Page Da thanh toan", billDetailService.PageGetAllBillDetailsDaThanhToan(pageable))
        );
    }

    @GetMapping("/da-thanh-toan")
    public ResponseEntity<ResponseObject> daHuyPage(@RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo
            , @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize){
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Page Da thanh toan", billDetailService.PageGetAllBillDetailsDaHuy(pageable))
        );
    }

    @GetMapping("/da-thanh-toan")
    public ResponseEntity<ResponseObject> dangVanChuyenPage(@RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo
            , @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize){
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Page Da thanh toan", billDetailService.PageGetAllBillDetailsDaThanhToan(pageable))
        );
    }

    @GetMapping("/da-thanh-toan")
    public ResponseEntity<ResponseObject> daVanChuyenPage(@RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo
            , @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize){
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Page Da thanh toan", billDetailService.PageGetAllBillDetailsVanChuyen(pageable))
        );
    }

    @GetMapping("/da-thanh-toan")
    public ResponseEntity<ResponseObject> dangHoanPage(@RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo
            , @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize){
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Page Da thanh toan", billDetailService.PageGetAllBillDetailsDangHoan(pageable))
        );
    }

    @GetMapping("/da-thanh-toan")
    public ResponseEntity<ResponseObject> daHoanKiemTraPage(@RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo
            , @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize){
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Page Da thanh toan", billDetailService.PageGetAllBillDetailsDaHoanKiemTra(pageable))
        );
    }

    @GetMapping("/da-thanh-toan")
    public ResponseEntity<ResponseObject> daTraHoanTienPage(@RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo
            , @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize){
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Page Da thanh toan", billDetailService.PageGetAllBillDetailsDaTraHoanTien(pageable))
        );
    }

    @GetMapping("/da-thanh-toan")
    public ResponseEntity<ResponseObject> daDoiTraPage(@RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo
            , @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize){
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Page Da thanh toan", billDetailService.PageGetAllBillDetailsDoiTra(pageable))
        );
    }
}
