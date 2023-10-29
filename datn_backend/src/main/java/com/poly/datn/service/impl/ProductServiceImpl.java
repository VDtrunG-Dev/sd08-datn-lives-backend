package com.poly.datn.service.impl;

import com.poly.datn.model.TProduct;
import com.poly.datn.repository.IProductRepository;
import com.poly.datn.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Page<TProduct> getAllProducts(int pageNumber) {
        return productRepository.findAll(PageRequest.of(pageNumber,5));
    }

    @Override
    public Optional<TProduct> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public String createProduct(TProduct product) {
        List<TProduct> foundProducts = productRepository.findByName(product.getName().trim());
        if (foundProducts.size() > 0) {
            // Xử lý lỗi, ví dụ:
            throw new IllegalArgumentException("Product name already taken");
        }
        productRepository.save(product);

        return "Thêm Thành Công";
    }

    @Override
    public String updateProduct(Long id,TProduct updatedProduct) {
        TProduct product = productRepository.findByIdProduct(id);

        product.setName(updatedProduct.getName());
        productRepository.save(product);

        return "Thêm thành công";
    }

    @Override
    public String deleteProduct(Long id) {
        TProduct product = productRepository.findByIdProduct(id);

        try {
            product.setStatus(0);
            productRepository.save(product);
        }catch (Exception e){
            return "Xoá Thất Bại";
        }
        return "Xoá Thành Công";
    }
}
