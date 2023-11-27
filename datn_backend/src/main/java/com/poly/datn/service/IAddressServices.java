package com.poly.datn.service;

import com.poly.datn.model.TAddress;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAddressServices {


    Page<TAddress> findAll(int pageNumber,String keyword);

    String addAddress(TAddress address);

    String deleteAddressById(Long id);

    String updateAddress(TAddress address);

    TAddress findById(Long id);

    List<TAddress> search(String keyword);
}
