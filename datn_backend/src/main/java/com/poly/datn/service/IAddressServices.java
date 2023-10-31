package com.poly.datn.service;

import com.poly.datn.model.TAddress;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAddressServices {


    Page<TAddress> findAllPage(int pageNumber);

    List<TAddress> findAll();

    String addAddress(TAddress address);

    String deleteAddressById(Long id);

    String updateAddress(TAddress address);

    List<TAddress> findByKeywork(String keyword);

    TAddress findById(Long id);

    List<TAddress> findByStatus0();
}
