package com.poly.datn.service;

import com.poly.datn.model.TProduct;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    TProduct findByName(String name);

    List<TProduct> getAllProducts(int page);

    Optional<TProduct> getProductById(Long id);

    String createProduct(TProduct product);

    String updateProduct(Long id, TProduct updatedProduct);

    String deleteProduct(Long id);

    List<TProduct> findByKeyword(String keyword);

    String activeProduct(Long id);
}
