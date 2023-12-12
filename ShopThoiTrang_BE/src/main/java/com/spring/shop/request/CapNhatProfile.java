package com.spring.shop.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CapNhatProfile {
    @NotBlank(message = "Không được bỏ trống")
    private String Fullname;
    private String Image;
    @NotBlank(message = "Không được bỏ trống")
    @Pattern(regexp = "^\\+?(?:0|84)\\d{9,10}$", message = "Số điện thoại không hợp lệ")
    private String Phone;
    @NotBlank(message = "Không được bỏ trống")
    @Email(message = "Email không hợp lệ")
    private String Email;
    private Boolean Gender;
}
