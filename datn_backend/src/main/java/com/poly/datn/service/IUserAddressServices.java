package com.poly.datn.service;

import com.poly.datn.model.TAddress;
import com.poly.datn.model.TUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserAddressServices {

    List<TAddress> findAddressByUser(Long idUser);

    String saveUserAddress(TUser user, TAddress address);

    String deleteUserAddress(Long id);
}
