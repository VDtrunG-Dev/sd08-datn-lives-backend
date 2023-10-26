package com.poly.datn.controller.admin;

import com.poly.datn.model.TPointTransactions;
import com.poly.datn.service.IPointTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/pointtransaction/")
public class PointTransactionController {

    @Autowired
    private IPointTransactionService pointService;


    @GetMapping("/view")
    public ResponseEntity<List<TPointTransactions>> getAllTransactions() {
        return ResponseEntity.ok(pointService.getAllTransactions());
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<TPointTransactions>> getAllTransactionsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        return ResponseEntity.ok(pointService.getAllTransactionsPaginated(page, size));
    }
}
