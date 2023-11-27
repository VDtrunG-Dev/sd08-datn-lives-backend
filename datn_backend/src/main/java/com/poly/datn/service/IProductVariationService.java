package com.poly.datn.service;
import com.poly.datn.model.TProductVariation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
public interface IProductVariationService {
    List<TProductVariation> getAllProductVariations();
    Optional<TProductVariation> getProductVariationById(Long id);
    TProductVariation createProductVariation(TProductVariation productVariation);
    TProductVariation updateProductVariation(Long id, TProductVariation updatedProductVariation);
    void deleteProductVariation(Long id);
    List<TProductVariation> searchAll(String name, String sku, String description);
    Page<TProductVariation> getAllStatus(Integer page);
    Page<TProductVariation> getActiveProductVariations(Integer status, Pageable pageable);
    List<TProductVariation> searchByKeyword(String keyword);
}