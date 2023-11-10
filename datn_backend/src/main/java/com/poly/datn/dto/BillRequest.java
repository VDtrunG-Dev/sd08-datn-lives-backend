package com.poly.datn.dto;

import com.poly.datn.model.TShippingMethod;
import com.poly.datn.model.TUser;
import com.poly.datn.model.TVoucher;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.sql.Date;

public class BillRequest {

    private Long id;

    private TUser customer;

    private TVoucher voucher;

    private TUser staff;

    private TShippingMethod shippingMethod;

    private String billCode;

    private String recipientPhone;

    private BigDecimal cash;

    private BigDecimal moneyTransfer;

    private BigDecimal totalAmount;

    private BigDecimal discount;

    private BigDecimal totalAmountAfterDiscount;

    private String province;

    private String district;

    private String ward;

    private String detailAddress;

    private String createdBy;

    private String updatedBy;

    private Date createdAt;

    private Date updatedAt;

    private Integer status;
}
