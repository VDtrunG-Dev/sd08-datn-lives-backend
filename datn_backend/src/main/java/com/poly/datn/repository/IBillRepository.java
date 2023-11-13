package com.poly.datn.repository;

import com.poly.datn.model.TBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBillRepository extends JpaRepository<TBill, Long> {
    List<TBill> findAll();
    Page<TBill> findByStatus(Integer status, Pageable pageable);

    @Query("SELECT tb FROM TBill tb WHERE " +
            "tb.billCode LIKE %:searchTerm% OR " +
            "tb.recipientPhone LIKE %:searchTerm% OR " +
            "tb.province LIKE %:searchTerm% OR " +
            "tb.district LIKE %:searchTerm% OR " +
            "tb.ward LIKE %:searchTerm% OR " +
            "tb.detailAddress LIKE %:searchTerm% OR " +
            "tb.createdBy LIKE %:searchTerm% OR " +
            "tb.updatedBy LIKE %:searchTerm%")
    List<TBill> searchTBillBySearchTerm(String searchTerm);


}
