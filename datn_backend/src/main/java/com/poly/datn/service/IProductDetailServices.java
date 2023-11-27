package com.poly.datn.service;

import com.poly.datn.dto.ProductDTO;
import com.poly.datn.dto.ProductVariantDTO;
import com.poly.datn.model.TProductVariation;

import java.util.List;

public interface IProductDetailServices {

    String addProductDetail(ProductDTO productDTO);

    String updateProductDetails(TProductVariation productVariation);

    String activeProductDetail(Long id);

    String deleteProductDetail(Long id);

    TProductVariation findByName(ProductVariantDTO productVariantDTO);

    List<TProductVariation> findAll();
}
