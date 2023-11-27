package com.poly.datn.service;

import com.poly.datn.model.TProduct;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    TProduct findByName(String name);

    List<String> displayNameProduct(String search);

    Page<TProduct> getProducts(int page,String search);

    Page<TProduct> findAll(int page,String search);

    Optional<TProduct> getProductById(Long id);

    String createProduct(String name);

    String updateProduct(Long id, TProduct updatedProduct);

    String deleteProduct(Long id);

    String activeProduct(Long id);
}
