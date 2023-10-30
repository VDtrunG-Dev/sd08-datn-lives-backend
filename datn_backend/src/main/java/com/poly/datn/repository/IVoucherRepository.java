package com.poly.datn.repository;

import com.poly.datn.model.TPointTransactions;
import com.poly.datn.model.TVoucher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IVoucherRepository extends JpaRepository<TVoucher, Long> {

    List<TVoucher> findByStatus(int status);



    Page<TVoucher> findByStatus(int status, Pageable pageable);

}
