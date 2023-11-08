package com.poly.datn.service.impl;

import com.poly.datn.model.TProductVariation;
import com.poly.datn.repository.IProductVariationRepository;
import com.poly.datn.service.IProductVariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductVariationServiceImpl implements IProductVariationService {
    @Autowired
    private IProductVariationRepository  productVariationRepository;

    @Override
    public List<TProductVariation> getAllProductVariations() {
        return productVariationRepository.findAll();
    }

    @Override
    public Optional<TProductVariation> getProductVariationById(Long id) {
        return productVariationRepository.findById(id);
    }

    @Override
    public TProductVariation createProductVariation(TProductVariation productVariation) {
        return productVariationRepository.save(productVariation);
    }

    @Override
    public TProductVariation updateProductVariation(Long id, TProductVariation updatedProductVariation) {
        return productVariationRepository.findById(id)
                .map(existingProductVariation -> {
                    existingProductVariation.setProduct(updatedProductVariation.getProduct());
                    existingProductVariation.setSku(updatedProductVariation.getSku());
                    existingProductVariation.setName(updatedProductVariation.getName());
                    existingProductVariation.setPrice(updatedProductVariation.getPrice());
                    existingProductVariation.setPriceNow(updatedProductVariation.getPriceNow());
                    existingProductVariation.setQuantity(updatedProductVariation.getQuantity());
                    existingProductVariation.setView(updatedProductVariation.getView());
                    existingProductVariation.setAvatar(updatedProductVariation.getAvatar());
                    existingProductVariation.setDescription(updatedProductVariation.getDescription());
                    existingProductVariation.setCreatedBy(updatedProductVariation.getCreatedBy());
                    existingProductVariation.setUpdatedBy(updatedProductVariation.getUpdatedBy());
                    existingProductVariation.setStatus(updatedProductVariation.getStatus());
                    return productVariationRepository.save(existingProductVariation);
                })
                .orElse(null);
    }

    @Override
    public void deleteProductVariation(Long id) {
        productVariationRepository.deleteById(id);
    }

    @Override
    public List<TProductVariation> searchAll(String name, String sku, String description) {
        return productVariationRepository.findAll().stream()
                .filter(roleByName -> roleByName.getName().contains(name))
                .filter(roleByName -> roleByName.getDescription().contains(description))
                .filter(roleByName -> roleByName.getSku().contains(sku))
                .collect(Collectors.toList());

    }

    @Override
    public Page<TProductVariation> getAllStatus(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return productVariationRepository.findAll(pageRequest);
    }

    @Override
    public Page<TProductVariation> getActiveProductVariations(Integer status, Pageable pageable) {
        return productVariationRepository.findByStatus(status, pageable);
    }

    @Override
    public List<TProductVariation> searchByKeyword(String keyword) {
        return productVariationRepository.findAll().stream()
                .filter(productVariation -> productVariation.getSku().contains(keyword)
                        || productVariation.getName().contains(keyword)
                        || productVariation.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }


}
