package com.spring.shop.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class BillRequest {
    private BigDecimal TotalPrice;
    private BigDecimal ShipPrice;
    private BigDecimal TotalPriceLast;
    private String Note;
    private Integer PayType;
    private Integer PayStatus;
    private Integer IdCoupon;
    private Integer IdAddress;
    private Integer IdCustomer;
    private Integer Status;
    private Integer TypeStatus;

}
