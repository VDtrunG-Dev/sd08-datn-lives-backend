package com.poly.datn.repository;

import com.poly.datn.model.TProductVariation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductVariationRepository extends JpaRepository<TProductVariation,Long> {

    List<TProductVariation> findAll();
    Page<TProductVariation> findByStatus(Integer status, Pageable pageable);
}
