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

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServicesImpl implements IUserServices {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private IUserAddressRepository userAddressRepository;

    @Override
    public Page<TUser> findAllPage(int pageNumber) {
        return userRepository.findAll(PageRequest.of(pageNumber,5));
    }

    @Override
    public List<TUser> findAll() {
        return userRepository.findAll();
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

    @Override
    public String updateUser(TUser user) {
        String validate = validate(user);
        if (validate == null){
            userRepository.save(user);
            return "Cập Nhập Thành Công";
        }
        return validate;
    }

    @Override
    public String active(Long id) {
        TUser user = userRepository.findByIdUser(id);
        user.setStatus(1);
        userRepository.save(user);
        return "Cập Nhập Thành Công";
    }

    @Override
    public List<TUser> findByKeyword(String keyword) {
        List<TUser> allUser = userRepository.findAll();

        return allUser.stream().filter(
                user -> user.getFirstName().contains(keyword) ||
                        user.getLastName().contains(keyword) ||
                        user.getEmail().contains(keyword) ||
                        user.getPhoneNumber().contains(keyword)
        ).collect(Collectors.toList());
    }

    @Override
    public List<TUser> findByStatus(int status) {
        return userRepository.findByStatus(status);
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

    private String validate(TUser user){
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

