package com.poly.datn.service;

import com.poly.datn.dto.OptionValueDTO;
import com.poly.datn.model.TOptionValue;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IOptionValueServices {

    Page<TOptionValue> findAll(int pageNumber);

    List<String> findByOptionId(Long optionId);

    TOptionValue findById(Long id);

    String save(OptionValueDTO optionValueDto);

    String deleteById(Long id);

    String update(TOptionValue optionValue);

    String active(Long id);

    TOptionValue findByName(String name);
}
