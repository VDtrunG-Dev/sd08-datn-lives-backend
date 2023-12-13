package com.spring.shop.response;

import java.math.BigDecimal;
import java.util.Date;

public interface BillAllResponse {
    Integer getId();
    String getCode();
    Date getPurchaseDate();
    Date getEstimatedDate();
    Date getPaymentDate();
    Date getDelyveryDate();
    BigDecimal getTotalPrice();
    BigDecimal getShipPrice();
    BigDecimal getTotalPriceLast();
    String getNote();
    Integer getPayType();
    Integer getPayStatus();
    String getFullname();
    String getPhone();
    String getAddress();
    String getCityName();
    String getDistrictName();
    String getWardName();
    String getCityId();
    String getDistrictId();
    String getWardId();
    Integer getIdCoupon();
    Integer getIdCustomer();
    Integer getIdVoucher();
    Integer getIdEmployee();
    Integer getStatus();
    String getUsername();

}
