package com.poly.datn.service.impl;

import com.poly.datn.model.TUser;
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
        userRepository.delete(user);
        return "Xoá Thành Công";
    }

    @Override
    public String saveUser(TUser user) {
        String validate = validateUser(user);
        if (validate == null){
            user.setStatus(1);
            userRepository.save(user);
            return "Thêm thành công";
        }
        return validate;
    }



    private String validateUser(TUser user){
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
