package com.poly.datn.controller.admin;


import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TRank;
import com.poly.datn.service.impl.RankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
@RequestMapping("/api/admin/rank")
public class RankController {

    @Autowired
    private RankServiceImpl rankService;

    // hiển thị rank
    @GetMapping("view")
    public List<TRank> getALl() {
        return rankService.getAllRank();
    }


    // phân trang
    @GetMapping("/paged")
    public ResponseEntity<ResponseObject> getAllRanksPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        int tongRank = rankService.getAllRank().size();
        int soTrang = tongRank / size;
        if (page > soTrang){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                    "ok", "Không có dữ liệu", ""));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                "ok", "Phân trang thành công.", rankService.getAllPaged(page, size)));
    }


    // xóa theo id

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteRank(@PathVariable Long id) {
        boolean isDeleted = rankService.deleteRank(id);

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Xóa thành công ", ""));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Thất bại", "Không thể xóa", rankService.deleteRank(id)));
        }
    }


    // thêm mới

    @PostMapping("/create")
    public ResponseEntity<String> addRank(@RequestBody TRank rank) {
        TRank savedRank = rankService.createRank(rank);
        if (savedRank != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Thêm thành công .");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while saving the rank.");
        }
    }


    // cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<String> updateRank(@PathVariable Long id, @RequestBody TRank rank) {

        TRank updatedRank = rankService.updateRank(id, rank);


        if (updatedRank != null) {
            return ResponseEntity.ok("Cập nhật thành công.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy  với ID được cung cấp.");
        }


    }


    // tìm kiếm theo id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<TRank> foundRank = rankService.getRankById(id);
        if (foundRank.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Tìm kiếm rank thành công!", foundRank)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Không thể tìm thấy rank có id  = " + id, "")
            );
        }
    }


    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDBExceptions(DataAccessException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi cơ sở dữ liệu.");
    }


    // hiển thị theo trạng thái

    @GetMapping("/bystatus")
    public ResponseEntity<List<TRank>> getAllByStatus() {
        List<TRank> ranks = rankService.getAllRanksByStatus(0);
        if (ranks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(ranks);
    }


    // tìm kiếm tất cả
    @GetMapping("/search-all/")
    public List<TRank> searchRanks(@RequestParam(required = false) String rankName,
                                   @RequestParam(required = false) Integer minimumPoints,
                                   @RequestParam(required = false) Integer status) {
        return rankService.searchAll(rankName, minimumPoints, status);
    }


    // xóa ảo

    @PutMapping("markAsDeleted/{id}")
    public ResponseEntity<ResponseObject> markRankAsDeleted(@PathVariable Long id) {
        Optional<TRank> rankOptional = rankService.getRankById(id);
        if (rankOptional.isPresent()) {
            TRank rank = rankOptional.get();
            rank.setStatus(0);

            TRank updatedRank = rankService.updateRank(id, rank);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Đánh dấu xóa thành công", updatedRank)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tồn tại rank này", null)
            );
        }
    }


    // khôi phục xóa ảo
    @PutMapping("restore/{id}")
    public ResponseEntity<ResponseObject> restoreRank(@PathVariable Long id) {
        Optional<TRank> rankOptional = rankService.getRankById(id);
        if (rankOptional.isPresent()) {
            TRank rank = rankOptional.get();


            if (rank.getStatus() == 0) {
                rank.setStatus(1);
                TRank restoredRank = rankService.updateRank(id, rank);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Khôi phục thành công", restoredRank)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ResponseObject("failed", "Rank này chưa bị xóa", null)
                );
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tồn tại rank này", null)
            );
        }
    }


    @GetMapping("/byStatus/1/paged")
    public ResponseEntity<Page<TRank>> getAllByStatus1Paged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<TRank> ranks = rankService.getAllByStatusPaged(1, page, size);

        if (ranks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(ranks);
    }

    @GetMapping("/active")
    public Page<TRank> getActiveRanks(@RequestParam Integer status, @RequestParam Integer page) {
        return rankService.getActiveRank(status, page);
    }

    @GetMapping("/inactive")
    public Page<TRank> getInactiveRanks(@RequestParam Integer status, @RequestParam Integer page) {
        return rankService.getInactiveRank(status, page);
    }


}
