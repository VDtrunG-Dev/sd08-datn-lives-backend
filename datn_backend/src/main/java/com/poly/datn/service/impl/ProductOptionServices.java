package com.poly.datn.service.impl;

import com.poly.datn.model.TOption;
import com.poly.datn.model.TProduct;
import com.poly.datn.model.TProductOption;
import com.poly.datn.repository.IProductOptionRepository;
import com.poly.datn.service.IProductOptionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOptionServices implements IProductOptionServices {

    @Autowired
    private IProductOptionRepository productOptionRepository;

    @Override
    public String save(TProduct product, List<TOption> options) {
        TProductOption productOption = new TProductOption();

        productOption.setProduct(product);
        try{
            for(TOption o : options){
                productOption.setOption(o);
                productOption.setStatus(1);
                productOptionRepository.save(productOption);
            }
        }catch (Exception e){
            return "Xảy Ra Lỗi, Thêm Thất Bại";
        }
        return "Thêm Thành Công";
    }

    @Override
    public String deleteById(Long id) {
        TProductOption productOption = productOptionRepository.findByIdProductOption(id);
        productOption.setStatus(0);
        productOptionRepository.save(productOption);
        return "Xoá Thành Công";
    }
}
