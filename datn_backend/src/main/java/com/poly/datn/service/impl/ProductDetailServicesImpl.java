package com.poly.datn.service.impl;

import com.poly.datn.dto.OptionDTO;
import com.poly.datn.dto.ProducDetailDTO;
import com.poly.datn.dto.ProductDTO;
import com.poly.datn.dto.ProductVariantDTO;
import com.poly.datn.model.*;
import com.poly.datn.repository.*;
import com.poly.datn.service.IProductDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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
                for(String ov : o.getOptionValueName()){
                    TVariantValue variantValue = new TVariantValue();
                    TOptionValue optionValue = optionValueRepository.findByNameAndOptionName(ov,o.getOptionName());
                    variantValue.setOptionValue(optionValue);
                    variantValue.setProductOption(productOption);
                    variantValue.setProduct(product);
                    variantValue.setStatus(1);
                    variantValueRepository.save(variantValue);
                }
            }
            List<ProducDetailDTO> listProductDetailName = productDTO.getProductDetails();
//            generateProductOptions(productDTO.getOption(),product.getName(),0,"",listProductDetailName);
            for (ProducDetailDTO p : listProductDetailName){
                TProductVariation productVariation = new TProductVariation();
                productVariation.setProduct(product);
                productVariation.setName(p.getName());
                productVariation.setPriceNow(p.getPriceNow());
                productVariation.setPrice(p.getPrice());
                productVariation.setAvatar(p.getAvatar());
                productVariation.setCreatedAt(LocalDate.now());
                productVariation.setStatus(1);
                productVariantRepository.save(productVariation);
            }
        }catch (Exception e){
            System.out.println(e);
            return "Thêm Thất Bại";
        }
        return "Thành Công";
    }


    private void generateProductOptions(List<OptionDTO> options, String productName, int currentIndex, String currentOption, List<String> result) {
        if (currentIndex == options.size()) {
            result.add(productName + " [" + currentOption + "]");
            return;
        }
        OptionDTO option = options.get(currentIndex);
        if (option.getOptionValueName() != null && !option.getOptionValueName().isEmpty()) {
            for (String value : option.getOptionValueName()) {
                String updatedOption = currentOption.isEmpty() ? value : currentOption + " - " + value;
                generateProductOptions(options, productName, currentIndex + 1, updatedOption, result);
            }
        } else {
            generateProductOptions(options, productName, currentIndex + 1, currentOption, result);
        }
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
            pVariation.setUpdatedAt(LocalDate.now());
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

    @Override
    public TProductVariation findByName(ProductVariantDTO productVariantDTO) {
        StringBuilder result = new StringBuilder(productVariantDTO.getProductName() + "[");
        for (int i = 0; i < productVariantDTO.getOptions().size(); i++) {
            result.append(productVariantDTO.getOptions().get(i));
            if (i < productVariantDTO.getOptions().size() - 1) {
                result.append(" - ");
            }
        }
        result.append("]");
        return productVariantRepository.findByName(result.toString());
    }

    @Override
    public List<TProductVariation> findAll() {
        return productVariantRepository.findAll();
    }


}
