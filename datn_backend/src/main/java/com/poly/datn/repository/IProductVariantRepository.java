package com.poly.datn.repository;

import com.poly.datn.model.TProductVariation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductVariantRepository extends JpaRepository<TProductVariation,Long> {

    @Query("SELECT pv FROM TProductVariation pv WHERE pv.id = :id")
    TProductVariation findByIdProductVariation(Long id);

    @Query("SELECT pv FROM TProductVariation pv WHERE pv.id = :id AND pv.status = 0")
    TProductVariation findByIdProductVariationActive(Long id);

    @Query("SELECT pv FROM TProductVariation pv WHERE pv.id = :id AND pv.status = 1")
    TProductVariation findByIdProductVariationDelete(Long id);

    @Query("SELECT pv FROM TProductVariation pv WHERE pv.name = :name")
    TProductVariation findByName(String name);

    List<TProductVariation> findAll();

    Page<TProductVariation> findByStatus(Integer status, Pageable pageable);
}
