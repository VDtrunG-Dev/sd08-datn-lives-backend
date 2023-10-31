package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TPointTransactions;
import com.poly.datn.service.IPointTransactionService;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/pointtransaction/")
public class PointTransactionController {

    @Autowired
    private IPointTransactionService pointTransactionService;


    @GetMapping("/view")
    public ResponseEntity<List<TPointTransactions>> getAllTransactions() {
        return ResponseEntity.ok(pointTransactionService.getAllTransactions());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<TPointTransactions> foundPointTransaction = pointTransactionService.getPointTransactionsById(id);
        if (foundPointTransaction.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Tìm kiếm pointtransaction thành công!", foundPointTransaction)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Không thể tìm thấy pointtransaction có id  = " + id, "")
            );
        }
    }

    @GetMapping("/paginated")
    public ResponseEntity<ResponseObject> getAllTransactionsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {


        int tongPointTransaction = pointTransactionService.getAllTransactions().size();
        int soTrang = tongPointTransaction / size;
        if (page > soTrang) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                    "ok", "Không có dữ liệu", ""));

        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                "ok", "Phân trang thành công.", pointTransactionService.getAllTransactionsPaginated(page, size)
        ));
    }


    @PostMapping("/create")
    public ResponseEntity<ResponseObject> addTransaction(@RequestBody TPointTransactions pointTransaction) {
        TPointTransactions savedTransaction = pointTransactionService.savePointTransaction(pointTransaction);
        if (savedTransaction != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseObject("ok", "Thêm mới pointtransaction thành công!", savedTransaction)
            );
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("false", "Thêm mới pointtransaction thất bại!", null)
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateTransaction(@PathVariable Long id, @RequestBody TPointTransactions pointTransaction) {
        if (pointTransactionService.existsById(id)) {
            pointTransaction.setTransactionId(id);
            TPointTransactions updatedTransaction = pointTransactionService.updatePointTransaction(id, pointTransaction);
            return ResponseEntity.ok(new ResponseObject("ok", "Cập nhật pointtransaction thành công!", updatedTransaction));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Không thể tìm thấy pointtransaction có id  = " + id, null)
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteTransaction(@PathVariable Long id) {
        if (pointTransactionService.deletePointTransactionById(id)) {
            return ResponseEntity.ok(new ResponseObject("ok", "Xóa pointtransaction thành công!", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Không thể xóa pointtransaction có id  = " + id, null)
            );
        }
    }


    @GetMapping("/bystatus")
    public ResponseEntity<List<TPointTransactions>> getAllByStatus(@RequestParam(defaultValue = "0") int status) {
        List<TPointTransactions> transactions = pointTransactionService.getAllByStatus(status);
        if (transactions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(transactions);
        }
        return ResponseEntity.ok(transactions);

    }


    // Xóa ảo
    @PutMapping("/markAsDeleted/{id}")
    public ResponseEntity<ResponseObject> markPointTransactionAsDeleted(@PathVariable Long id) {
        Optional<TPointTransactions> pointTransactionOptional = pointTransactionService.getPointTransactionsById(id);
        if (pointTransactionOptional.isPresent()) {
            TPointTransactions pointTransaction = pointTransactionOptional.get();
            pointTransaction.setStatus(0);

            TPointTransactions updatedPointTransaction = pointTransactionService.updatePointTransaction(id, pointTransaction);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Đánh dấu xóa point transaction thành công", updatedPointTransaction)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tồn tại point transaction này", null)
            );
        }
    }

    // Khôi phục xóa ảo
    @PutMapping("/restore/{id}")
    public ResponseEntity<ResponseObject> restorePointTransaction(@PathVariable Long id) {
        Optional<TPointTransactions> pointTransactionOptional = pointTransactionService.getPointTransactionsById(id);
        if (pointTransactionOptional.isPresent()) {
            TPointTransactions pointTransaction = pointTransactionOptional.get();

            if (pointTransaction.getStatus() == 0) {
                pointTransaction.setStatus(1);
                TPointTransactions restoredPointTransaction = pointTransactionService.updatePointTransaction(id, pointTransaction);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Khôi phục point transaction thành công", restoredPointTransaction)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ResponseObject("failed", "Point transaction này chưa bị xóa", null)
                );
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tồn tại point transaction này", null)
            );
        }
    }


    @GetMapping("/byStatus/1/paged")
    public ResponseEntity<Page<TPointTransactions>> getAllByStatus1Paged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<TPointTransactions> transactions = pointTransactionService.getAllByStatusPaged(1, page, size);

        if (transactions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/search")
    public List<TPointTransactions> searchPointTransactions(
            @RequestParam(value = "customerId", required = false) Long customerId,
            @RequestParam(value = "transactionType", required = false) Integer transactionType,
            @RequestParam(value = "transactionDate", required = false) Date transactionDate,
            @RequestParam(value = "transactionAmount", required = false) BigDecimal transactionAmount) {
        return pointTransactionService.searchAll(customerId, transactionType, transactionDate, transactionAmount);
    }


    @GetMapping("/searchByKeyword")
    public List<TPointTransactions> searchPointTransactionsByKeyword(@RequestParam("keyword") String keyword) {
        return pointTransactionService.searchByKeyword(keyword);
    }

}
