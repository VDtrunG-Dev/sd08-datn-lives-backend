package com.poly.datn.service.impl;

import com.poly.datn.dto.OptionDTO;
import com.poly.datn.dto.ProductDTO;
import com.poly.datn.model.*;
import com.poly.datn.repository.*;
import com.poly.datn.service.IProductDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductDetailServicesImpl implements IProductDetailServices {

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
                    productVariation.setStatus(1);
                    productVariantRepository.save(productVariation);
                }
            }
        }catch (Exception e){
            return "Thêm Thất Bại";
        }

        return "Thành Công";
    }

    @Override
    public String updateProductDetails(TProductVariation productVariation) {
        TProductVariation pVariation = productVariantRepository.findByIdProductVariation(productVariation.getId());
        if(pVariation == null){
            return "Sản Phẩm Không Tồn Tại";
        }
        try{
            pVariation.setPrice(productVariation.getPrice());
            pVariation.setAvatar(productVariation.getAvatar());
            pVariation.setQuantity(productVariation.getQuantity());
            pVariation.setPriceNow(productVariation.getPriceNow());
            pVariation.setDescription(productVariation.getDescription());
            productVariantRepository.save(pVariation);
        }catch (Exception e){
            return "Cập Nhập Thất Bại";
        }
        return "Cập Nhập Thành Công";

    }

    @Override
    public String activeProductDetail(Long id) {
        TProductVariation pVariation = productVariantRepository.findByIdProductVariationActive(id);
        if(pVariation == null){
            return "Sản Phẩm Đã Xoá Không Tồn Tại";
        }

        pVariation.setStatus(1);
        productVariantRepository.save(pVariation);
        return "Cập Nhật Thành Công";
    }

    @Override
    public String deleteProductDetail(Long id) {
        TProductVariation pVariation = productVariantRepository.findByIdProductVariationDelete(id);
        if(pVariation == null){
            return "Sản Phẩm Không Tồn Tại";
        }

        pVariation.setStatus(0);
        productVariantRepository.save(pVariation);
        return "Cập Nhật Thành Công";
    }


}
