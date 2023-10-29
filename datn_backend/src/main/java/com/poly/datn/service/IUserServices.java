package com.poly.datn.service;

import com.poly.datn.dto.UserDTO;
import com.poly.datn.model.TUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IUserServices {

    Page<TUser> findAll(int pageNumber);

    TUser findById(Long id);

    TUser findByEmail(String email);

    String deleteUserById(Long id);

    String deleteUserByEmail(String email);

    String saveUser(UserDTO userDTO);

    String active(Long id);
}
