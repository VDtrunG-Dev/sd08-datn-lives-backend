package com.poly.datn.dto;

import java.math.BigDecimal;
import java.util.List;

public class CheckoutDTO {
    private List<Long> productId;
    private Long voucherId;
    private String firtName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String provider;
    private String district;
    private String ward;
    private String detailAddress;
    private BigDecimal totalPrice;
    private BigDecimal discount;
    private BigDecimal totalAmountAfterDiscount;
    private BigDecimal priceShipping;
    private boolean addAddress;
    private boolean payments;

}
