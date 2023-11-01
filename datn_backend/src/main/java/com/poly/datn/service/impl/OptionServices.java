package com.poly.datn.service.impl;

import com.poly.datn.model.TOption;
import com.poly.datn.repository.IOptionRepository;
import com.poly.datn.service.IOptionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServices implements IOptionServices {

    @Autowired
    private IOptionRepository optionRepository;

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
    public String save(TOption option) {

        String validate = validate(option);

        if (validate == null){
           try{
               option.setStatus(1);
               optionRepository.save(option);
           }catch (Exception e){
               return "Thêm Thất Bại";
           }
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


    private String validate(TOption option){
        TOption optionById = optionRepository.findTOptionsByName(option.getName());
        if(option.getName().isEmpty()){
            return "Chưa Nhập Tên";
        }
        if(optionById != null){
            return "Option Đã Tồn Tại";
        }
        return null;
    }
}
