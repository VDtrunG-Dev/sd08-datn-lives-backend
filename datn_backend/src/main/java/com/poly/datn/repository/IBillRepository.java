package com.poly.datn.repository;

import com.poly.datn.model.TBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBillRepository extends JpaRepository<TBill, Long> {
    List<TBill> findAll();
    Page<TBill> findByStatus(Integer status, Pageable pageable);


}
