package com.poly.datn.service;

import com.poly.datn.model.TAddress;

import java.util.List;

public interface IAddressServices {


    List<TAddress> findAll();

    String addAddress(TAddress address);

    String deleteAddressById(Long id);
}
