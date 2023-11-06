package com.poly.datn.service.impl;

import com.poly.datn.model.TOption;
import com.poly.datn.model.TPoints;
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
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public TProduct findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<TProduct> getAllProducts(int pageNumber) {
        List<TProduct> products = productRepository.findAll();
        int page = (pageNumber - 1) * 10;
        int endPage = Math.min(page + 10, products.size());
        return products.subList(page,endPage);
    }

    @Override
    public Optional<TProduct> getProductById(Long id) {
        return productRepository.findById(id);
    }


    @Override
    public String createProduct(TProduct product) {
        TProduct foundProducts = productRepository.findByName(product.getName().trim());
        if (foundProducts != null) {
            return "Sản Phẩm Đã Tồn Tại";
        }
        productRepository.save(product);

        return "Thêm Thành Công";
    }

    @Override
    public String updateProduct(Long id,TProduct updatedProduct) {
        TProduct product = productRepository.findByIdProduct(id);

        if(product == null){
            return "Sản Phẩm Không Tồn Tại";
        }
        product.setName(updatedProduct.getName());
        productRepository.save(product);

        return "Cập Nhật thành công";
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

    @Override
    public List<TProduct> findByKeyword(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

    @Override
    public String activeProduct(Long id) {
        TProduct product = productRepository.findByIdProduct(id);
        if(product == null){
            return "Sản Phẩm Không Tồn Tại";
        }
        product.setStatus(1);
        productRepository.save(product);
        return "Cập Nhập Thành Công";
    }
}
