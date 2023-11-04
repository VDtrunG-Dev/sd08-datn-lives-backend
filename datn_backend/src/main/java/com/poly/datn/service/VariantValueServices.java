package com.poly.datn.service;

import com.poly.datn.model.TVariantValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VariantValueServices {

    List<TVariantValue> getAllVariantValues();
    Page<TVariantValue> getAll(Integer status, Pageable pageable);

    Optional<TVariantValue> getVariantValueById(Long id);

    TVariantValue createVariantValue(TVariantValue variantValue);

    TVariantValue updateVariantValue(Long id, TVariantValue updatedVariantValue);

    void deleteVariantValue(Long id);

    Page<TVariantValue> getInActiveVariantValues(Integer status, Pageable pageable);
    Page<TVariantValue> getAllStatus(Integer page);
}
