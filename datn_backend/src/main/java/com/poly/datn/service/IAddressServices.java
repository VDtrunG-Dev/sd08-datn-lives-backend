package com.poly.datn.service;

import com.poly.datn.model.TAddress;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAddressServices {


    Page<TAddress> findAll(int pageNumber);

    String addAddress(TAddress address);

    String deleteAddressById(Long id);

    String updateAddress(TAddress address);

    String active(Long id);
}
