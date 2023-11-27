package com.poly.datn.service;


import com.poly.datn.model.TProductVariation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductVariantServices {
    List<TProductVariation> getAllProductVariations();

    Optional<TProductVariation> getProductVariationById(Long id);

    TProductVariation createProductVariation(TProductVariation productVariation);

    TProductVariation updateProductVariation(Long id, TProductVariation updatedProductVariation);

    void deleteProductVariation(Long id);
    Page<TProductVariation> getAllStatus(Integer page);
    Page<TProductVariation> getActiveProductVariations(Integer status, Pageable pageable);
    Page<TProductVariation> searchAll(String name, String sku, String description, Pageable pageable);

    Page<TProductVariation> searchByKeyword(String keyword, Pageable pageable);

}
