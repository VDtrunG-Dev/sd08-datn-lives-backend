package com.spring.shop.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgetForm {
    @NotBlank(message = "Tài Không được bỏ trống")
    private String Username;
    @NotBlank(message = " Email Không được bỏ trống")
    private String Email;
}
