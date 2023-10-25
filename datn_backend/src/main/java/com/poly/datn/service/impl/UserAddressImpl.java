package com.poly.datn.service.impl;

import com.poly.datn.model.TAddress;
import com.poly.datn.model.TUser;
import com.poly.datn.model.TUserAddress;
import com.poly.datn.repository.IUserAddressRepository;
import com.poly.datn.service.IUserAddressServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAddressImpl implements IUserAddressServices {

    @Autowired
    private IUserAddressRepository userAddressRepository;

    @Override
    public String saveUserAddress(TUser user, TAddress address) {
        TUserAddress userAddress = new TUserAddress();
        userAddress.setCustomer(user);
        userAddress.setAddress(address);
        try{
            userAddressRepository.save(userAddress);
        }catch (Exception e){
            return "Thêm thất bại";
        }
        return "Thêm Thành Công";
    }

    @Override
    public String deleteUserAddress(Long id) {
        TUserAddress userAddress = userAddressRepository.findByIdUserAddress(id);
        if(userAddress == null){
            return "Không tồn tại";
        }
        try{
            userAddressRepository.delete(userAddress);
        }catch (Exception e){
            return "Xoá Thất Bại";
        }
        return "Xoá Thành Công";
    }
}
