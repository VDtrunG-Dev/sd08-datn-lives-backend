package com.poly.datn.service.impl;

import com.poly.datn.dto.UserDTO;
import com.poly.datn.model.TAddress;
import com.poly.datn.model.TUser;
import com.poly.datn.model.TUserAddress;
import com.poly.datn.repository.IAddressRepository;
import com.poly.datn.repository.IUserAddressRepository;
import com.poly.datn.repository.IUserRepository;
import com.poly.datn.service.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class UserServicesImpl implements IUserServices {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private IUserAddressRepository userAddressRepository;

    @Override
    public Page<TUser> findAll(int pageNumber) {
        return userRepository.findAll(PageRequest.of(pageNumber,2));
    }

    @Override
    public TUser findById(Long id) {
        return userRepository.findByIdUser(id);
    }

    @Override
    public TUser findByEmail(String email) {
        return userRepository.findByEmailUser(email);
    }

    @Override
    public String deleteUserById(Long id) {
        TUser user = findById(id);
        if (user == null){
            return "Tài Khoản Không Tồn Tại";
        }else {
            user.setStatus(0);
            userRepository.save(user);
        }
        return "Xoá Thành Công";
    }

    @Override
    public String deleteUserByEmail(String email) {
        TUser user = findByEmail(email);
        if (user == null){
            return "Tài Khoản Không Tồn Tại";
        }
        else {
            user.setStatus(0);
            userRepository.save(user);
        }

        return "Xoá Thành Công";
    }

    @Override
    public String saveUser(UserDTO userDto) {
        String validate = validateUser(userDto);
        if (validate == null){
            //Set User
            TUser user = new TUser();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setEmail(userDto.getEmail());
            user.setCccd(userDto.getCccd());
            user.setDateOfBirth(userDto.getDateOfBirth());
            user.setPassword(userDto.getPassword());
            user.setStatus(1);
            userRepository.save(user);

            // Set Address
            TAddress address = new TAddress();
            address.setProvince(userDto.getProvince());
            address.setDistrict(userDto.getDistrict());
            address.setWard(userDto.getWard());
            address.setDetailAddress(userDto.getDetailAddress());
            address.setStatus(1);
            addressRepository.save(address);

            //Set UserAddress
            TUserAddress userAddress = new TUserAddress();
            userAddress.setAddress(address);
            userAddress.setCustomer(user);
            userAddress.setStatus(1);
            userAddressRepository.save(userAddress);

            return "Thêm thành công";
        }
        return validate;
    }



    private String validateUser(UserDTO user){
        if (user.getEmail().isEmpty()){
            return "Chưa Nhập Email";
        }

        if(user.getPhoneNumber().isEmpty()){
            return "Chưa Nhập Số Điện Thoại";
        }

        if(user.getFirstName().isEmpty()){
            return "Chưa Nhập Họ";
        }

        if(user.getLastName().isEmpty()){
            return "Chưa nhập tên";
        }

        if(user.getPassword().isEmpty()){
            return "Chưa nhập mật khẩu";
        }
        return null;
    }
}
