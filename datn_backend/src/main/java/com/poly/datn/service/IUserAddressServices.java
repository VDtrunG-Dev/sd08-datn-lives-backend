package com.poly.datn.service;

import com.poly.datn.model.TAddress;
import com.poly.datn.model.TUser;
import org.springframework.stereotype.Service;

@Service
public interface IUserAddressServices {

    String saveUserAddress(TUser user, TAddress address);

    String deleteUserAddress(Long id);
}
