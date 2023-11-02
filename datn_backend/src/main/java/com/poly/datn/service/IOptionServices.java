package com.poly.datn.service;

import com.poly.datn.dto.OptionDTO;
import com.poly.datn.model.TOption;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOptionServices {

    Page<TOption> findAll(int page);

    TOption findById(Long id);

    TOption findByName(String name);

    String save(OptionDTO optionDto);

    String delete(Long id);

    String update(TOption option);

    String active(Long id);

    List<TOption> searchOption(String keyword);

}
