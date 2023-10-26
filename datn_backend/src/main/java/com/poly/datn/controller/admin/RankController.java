package com.poly.datn.controller.admin;


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


@RestController
@RequestMapping("/api/admin/rank")
public class RankController {

    @Autowired
    private RankServiceImpl rankService;


    @GetMapping("view")
    public List<TRank> getALl() {
        return rankService.getAllRank();
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<TRank>> getAllRanksPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        return ResponseEntity.ok(rankService.getAllPaged(page, size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRank(@PathVariable Long id) {
        boolean isDeleted = rankService.deleteRank(id);

        if (isDeleted) {
            return ResponseEntity.ok("Rank deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rank not found with the provided ID.");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> addRank(@RequestBody TRank rank) {
        TRank savedRank = rankService.createRank(rank);
        if (savedRank != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Rank added successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while saving the rank.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRank(@PathVariable Long id, @RequestBody TRank rank) {

        TRank updatedRank = rankService.updateRank(id, rank);


        if (updatedRank != null) {
            return ResponseEntity.ok("Rank updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rank not found with the provided ID.");
        }


    }


    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDBExceptions(DataAccessException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred.");
    }


}
