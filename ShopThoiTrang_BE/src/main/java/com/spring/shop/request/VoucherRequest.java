package com.spring.shop.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class VoucherRequest {

    @NotBlank(message = "Mã không được bỏ trống !")
    private String Code;

    @NotBlank(message = "Tên không được bỏ trống !")
    private String Name;


    private Boolean TypeVoucher;


    private Boolean IsVoucher;


    private Integer Discount;


    private BigDecimal Cash;


    private Date StartDate;


    private Date EndDate;


    private Integer Minimum;



}
