package com.poly.datn.repository;

import com.poly.datn.model.TVariantValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVariantValueRepository extends JpaRepository<TVariantValue,Long> {
    List<TVariantValue> findAll();
    Page<TVariantValue> findByStatus(Integer status, Pageable pageable);
}
