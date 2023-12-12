package com.spring.shop.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerReques {

    private String code;
    @NotBlank(message = "Ten không được bỏ trống !")
    @Size(max = 100,message = " Ma có độ dài nhỏ hơn hoặc bằng 100 kí tự")
    private String fullname;
    @NotBlank(message = "Tai Khoan không được bỏ trống !")
    private String username;
    @NotBlank(message = "Mat Khau không được bỏ trống !")
    private String password;
    private String image;
    private Boolean gender;
    @NotBlank(message = "So Dien Thoai không được bỏ trống !")
    @Pattern(regexp = "^\\+?(?:0|84)\\d{9,10}$", message = "Số điện thoại không hợp lệ")
    private String phone;
    @NotBlank(message = "Email không được bỏ trống !")
    @Email(message = "Email không hợp lệ")
    private String email;

}
