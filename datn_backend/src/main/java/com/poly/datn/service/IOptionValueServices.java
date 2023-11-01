package com.poly.datn.service;

import com.poly.datn.model.TOptionValue;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IOptionValueServices {

    List<TOptionValue> findAll(int pageNumber);

    TOptionValue findById(Long id);

    String save(TOptionValue optionValue,Long idOption);

    String deleteById(Long id);

    String update(TOptionValue optionValue);

    String active(Long id);
}
