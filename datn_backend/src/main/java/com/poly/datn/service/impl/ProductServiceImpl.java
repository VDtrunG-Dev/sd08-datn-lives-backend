package com.poly.datn.service.impl;

import com.poly.datn.model.TProduct;
import com.poly.datn.repository.IProductRepository;
import com.poly.datn.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<TProduct> getAllProducts() {
        return productRepository.findByStatus(1);
    }

    @Override
    public Optional<TProduct> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public TProduct createProduct(TProduct product) {
        // Đưa logic kiểm tra trùng tên sản phẩm ở đây (đối với việc tạo mới sản phẩm)
        // Nếu tên sản phẩm đã tồn tại, xử lý kiểm tra và trả về lỗi hoặc thực hiện thêm sản phẩm mới
        List<TProduct> foundProducts = productRepository.findByName(product.getName().trim());
        if (foundProducts.size() > 0) {
            // Xử lý lỗi, ví dụ:
            throw new IllegalArgumentException("Product name already taken");
        }

        // Tiến hành thêm sản phẩm
        return productRepository.save(product);
    }

    @Override
    public TProduct updateProduct(Long id, TProduct updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setCreatedBy(updatedProduct.getCreatedBy());
                    existingProduct.setStatus(updatedProduct.getStatus());
                    existingProduct.setUpdatedAt(LocalDateTime.now());
                    return productRepository.save(existingProduct);
                })
                .orElseGet(() -> {
                    throw new IllegalArgumentException("Product not found");
                });
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).ifPresent(product -> productRepository.delete(product));
    }
}
