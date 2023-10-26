package com.poly.datn.repository;

import com.poly.datn.model.TVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IVoucherRepository extends JpaRepository<TVoucher, Long> {




}
