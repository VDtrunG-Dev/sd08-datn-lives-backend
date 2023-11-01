package com.poly.datn.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UserDTO {
    private String firstName;
//    private String role;
    private String lastName;
    private String cccd;
    private String email;
    private String phoneNumber;
    private String password;
    private String province;
    private String district;
    private String ward;
    private String detailAddress;

}
