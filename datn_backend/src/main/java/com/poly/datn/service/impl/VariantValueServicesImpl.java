package com.poly.datn.service.impl;

import com.poly.datn.model.TVariantValue;
import com.poly.datn.repository.IVariantValueRepository;
import com.poly.datn.service.VariantValueServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VariantValueServicesImpl implements VariantValueServices {
    @Autowired
    private IVariantValueRepository variantValueRepository;

    @Override
    public List<TVariantValue> getAllVariantValues() {
        return variantValueRepository.findAll();
    }

    @Override
    public Page<TVariantValue> getAll(Integer status, Pageable pageable) {
        return variantValueRepository.findByStatus(status, pageable);
    }

    @Override
    public Optional<TVariantValue> getVariantValueById(Long id) {
        return variantValueRepository.findById(id);
    }

    @Override
    public TVariantValue createVariantValue(TVariantValue variantValue) {
        return variantValueRepository.save(variantValue);
    }

    @Override
    public TVariantValue updateVariantValue(Long id, TVariantValue updatedVariantValue) {
        return variantValueRepository.findById(id)
                .map(existingVariantValue -> {
                    existingVariantValue.setProductOption(updatedVariantValue.getProductOption());
                    existingVariantValue.setOptionValue(updatedVariantValue.getOptionValue());
                    existingVariantValue.setStatus(updatedVariantValue.getStatus());
                    return variantValueRepository.save(existingVariantValue);
                })
                .orElse(null); // Handle error or return null if the variant value doesn't exist
    }

    @Override
    public void deleteVariantValue(Long id) {
        variantValueRepository.findById(id).ifPresent(variantValue -> variantValueRepository.delete(variantValue));
    }

    @Override
    public Page<TVariantValue> getInActiveVariantValues(Integer status, Pageable pageable) {
        return variantValueRepository.findByStatus(status, pageable);
    }

    @Override
    public Page<TVariantValue> getAllStatus(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return variantValueRepository.findAll(pageable);
    }
}
