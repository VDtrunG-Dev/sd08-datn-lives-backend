package com.poly.datn.service;

import com.poly.datn.model.TProduct;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    
    List<TProduct> getAllProducts();

    Optional<TProduct> getProductById(Long id);

    TProduct createProduct(TProduct product);

    TProduct updateProduct(Long id, TProduct updatedProduct);

    void deleteProduct(Long id);
}
