package com.poly.datn.service.impl;

import com.poly.datn.dto.OptionDTO;
import com.poly.datn.dto.ProductDTO;
import com.poly.datn.model.*;
import com.poly.datn.repository.*;
import com.poly.datn.service.ProductDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailServicesImpl implements ProductDetailServices {

    @Autowired
    private IOptionRepository optionRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IProductOptionRepository productOptionRepository;

    @Autowired
    private IOptionValueRepository optionValueRepository;

    @Autowired
    private IVariantValueRepository variantValueRepository;

    @Autowired
    private IProductVariantRepository productVariantRepository;


    @Override
    public String addProductDetail(ProductDTO productDTO) {
        try{
            TProduct product = productRepository.findByName(productDTO.getProductName());
            for(OptionDTO o : productDTO.getOption()){
                TProductOption productOption = new TProductOption();
                TOption option = optionRepository.findTOptionsByName(o.getOptionName());
                productOption.setProduct(product);
                productOption.setOption(option);
                productOption.setStatus(1);
                productOptionRepository.save(productOption);
                System.out.println(o.getOptionName());
                for(String ov : o.getOptionValueName()){
                    TVariantValue variantValue = new TVariantValue();
                    TOptionValue optionValue = optionValueRepository.findByNameAndOptionName(ov,o.getOptionName());
                    variantValue.setOptionValue(optionValue);
                    variantValue.setProductOption(productOption);
                    variantValue.setStatus(1);
                    variantValueRepository.save(variantValue);
                    TProductVariation productVariation = new TProductVariation();
                    productVariation.setQuantity(productDTO.getQuantity());
                    productVariation.setProduct(product);
                    productVariation.setAvatar(productDTO.getAvatar());
                    productVariation.setPriceNow(productDTO.getPriceNow());
                    productVariation.setPrice(productDTO.getPrice());
                    productVariantRepository.save(productVariation);
                }
            }
        }catch (Exception e){
            return "Thêm Thất Bại";
        }

        return "Thành Công";
    }


}
