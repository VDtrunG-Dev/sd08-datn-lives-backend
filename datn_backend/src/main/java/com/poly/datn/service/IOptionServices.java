package com.poly.datn.service;

import com.poly.datn.model.TOption;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOptionServices {

    Page<TOption> findAll(int page);

    TOption findById(Long id);

    TOption findByName(String name);

    String save(TOption option);

    String delete(Long id);

    String update(TOption option);

    String active(Long id);

    List<TOption> findByStatus(int status);
}
