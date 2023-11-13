package com.poly.datn.service.impl;

import com.poly.datn.dto.OptionValueDTO;
import com.poly.datn.model.TOption;
import com.poly.datn.model.TOptionValue;
import com.poly.datn.repository.IOptionRepository;
import com.poly.datn.repository.IOptionValueRepository;
import com.poly.datn.service.IOptionValueServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionValueServicesImpl implements IOptionValueServices {

    @Autowired
    private IOptionValueRepository optionValueRepository;

    @Autowired
    private IOptionRepository optionRepository;

    @Override
    public Page<TOptionValue> findAll(int pageNumber) {
        return optionValueRepository.findAll(PageRequest.of(pageNumber,10));
    }

    @Override
    public List<String> findByOptionId(Long optionId) {
        return optionValueRepository.getNameOptionByOptionId(optionId);
    }

    @Override
    public TOptionValue findById(Long id) {
        return optionValueRepository.findByIdOptionValue(id);
    }

    @Override
    public String save(OptionValueDTO optionValueDto) {
        String validate = validate(optionValueDto);
        TOption option = optionRepository.findTOptionsById(optionValueDto.getOptionId());
        TOptionValue optionValue = new TOptionValue();
        if(validate != null){
            return validate;
        }else {
            try{
                optionValue.setStatus(1);
                optionValue.setOption(option);
                optionValue.setValueName(optionValueDto.getOptionValueName());
                optionValueRepository.save(optionValue);
            }catch (Exception e){
                return "Thêm thất bại";
            }
        }

        return "Thêm thành công";
    }

    @Override
    public String deleteById(Long id) {
        TOptionValue optionValue = optionValueRepository.findByIdOptionValue(id);
        optionValue.setStatus(0);
        optionValueRepository.save(optionValue);
        return "Xoá Thành Công";
    }

    @Override
    public String update(TOptionValue optionValue) {
        TOptionValue optionV = optionValueRepository.findByIdOptionValue(optionValue.getId());
        optionV.setValueName(optionValue.getValueName());
        optionV.setOption(optionValue.getOption());
        return "Cập Nhập Thành Công";
    }

    @Override
    public String active(Long id) {
        TOptionValue optionValue = optionValueRepository.findByIdOptionValue(id);
        if(optionValue == null){
            return "Không tồn tại";
        }
        optionValue.setStatus(1);
        optionValueRepository.save(optionValue);
        return "Cập nhật thành công";
    }

    @Override
    public TOptionValue findByName(String optionValueName) {

        return optionValueRepository.findByName(optionValueName);
    }

    private String validate(OptionValueDTO optionValueDTO){
        TOptionValue optionV = optionValueRepository.findByName(optionValueDTO.getOptionValueName());

        if(optionV != null){
            return "Option Value Đã Tồn Tại";
        }
        if(optionValueDTO.getOptionValueName().isEmpty()){
            return "Chưa Nhập Option Value";
        }

        return null;
    }
}
