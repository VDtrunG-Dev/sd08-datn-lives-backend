package com.poly.datn.service.impl;

import com.poly.datn.dto.OptionDTO;
import com.poly.datn.model.TOption;
import com.poly.datn.model.TOptionValue;
import com.poly.datn.repository.IOptionRepository;
import com.poly.datn.repository.IOptionValueRepository;
import com.poly.datn.service.IOptionServices;
import com.poly.datn.service.IOptionValueServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Calendar;

@Service
public class OptionServicesImpl implements IOptionServices {

    @Autowired
    private IOptionRepository optionRepository;

    @Autowired
    private IOptionValueRepository optionValueRepository;

    @Override
    public Page<TOption> findAll(int page) {
        return optionRepository.findAll(PageRequest.of(page,5));
    }

    @Override
    public TOption findById(Long id) {
        return optionRepository.findTOptionsById(id);
    }

    @Override
    public TOption findByName(String name) {
        return optionRepository.findTOptionsByName(name);
    }

    @Override
    public String save(OptionDTO optionDTO) {
        TOption option = new TOption();
        String validate = validate(optionDTO);
        if (validate == null){
           try{
               option.setStatus(1);
               option.setName(optionDTO.getOptionName());
               optionRepository.save(option);
               for(String o : optionDTO.getOptionValueName()){
                   TOptionValue optionValue = new TOptionValue();
                   optionValue.setStatus(1);
                   optionValue.setValueName(o);
                   optionValue.setOption(option);
                   optionValueRepository.save(optionValue);
               }
           }catch (Exception e){
               return "Thêm Thất Bại";
           }
        }else {
            return validate;
        }
        return "Thêm Thành Công";
    }

    @Override
    public String delete(Long id) {

        TOption option = optionRepository.findTOptionsById(id);

        if(option == null){
            return "Option Không Tồn Tại";
        }
        try{
            option.setStatus(0);
            optionRepository.save(option);
        }catch (Exception e){
            return "Xoá Thất Bại";
        }

        return "Xoá Thành Công";
    }

    @Override
    public String update(TOption option) {
        TOption optionById = optionRepository.findTOptionsById(option.getId());

        if (optionById == null){
            return "Option Không Tồn Tại";
        }
        optionById.setName(option.getName());
        optionById.setStatus(option.getStatus());
        return "Thêm Thành Công";
    }

    @Override
    public String active(Long id) {
        TOption option = optionRepository.findTOptionsById(id);
        System.out.println(option);
        option.setStatus(1);
        optionRepository.save(option);
        return "Cập Nhập Thành Công";
    }

    @Override
    public List<TOption> searchOption(String keyword) {
        return optionRepository.findByNameContaining(keyword);
    }


    private String validate(OptionDTO optionDto){
        TOption optionById = optionRepository.findTOptionsByName(optionDto.getOptionName());
        if(optionDto.getOptionName().isEmpty()){
            return "Chưa Nhập Tên";
        }
        if(optionById != null){
            return "Option Đã Tồn Tại";
        }
        return null;
    }
}
