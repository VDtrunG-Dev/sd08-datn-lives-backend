package com.poly.datn.service;

import com.poly.datn.dto.ProductDTO;
import com.poly.datn.model.TProductVariation;

public interface IProductDetailServices {

    String addProductDetail(ProductDTO productDTO);

    String updateProductDetails(TProductVariation productVariation);

    String activeProductDetail(Long id);

    String deleteProductDetail(Long id);
}
