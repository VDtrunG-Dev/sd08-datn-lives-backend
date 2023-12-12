package com.spring.shop.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EmployeeRequest{
    private String Code;
    @NotBlank(message = "Không được bỏ trống Tên")
    private String Fullname;
    @NotBlank(message = "Không được bỏ trống UserName")
    private String Username;
    @NotBlank(message = "Không được bỏ trống PassWord")
    private String Password;

    private String Image;
    private Boolean Gender;
    @NotBlank(message = "Không được bỏ trống Phone")
    @Pattern(regexp = "^\\+?(?:0|84)\\d{9,10}$", message = "Số điện thoại không hợp lệ")
    private String Phone;
    @NotBlank(message = "Không được bỏ trống Email")
    @Email(message = "Email không hợp lệ")
    private String Email;

    private Integer IdRole;
}
