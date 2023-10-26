package com.poly.datn.dto.custom;

import java.math.BigDecimal;
import java.sql.Date;

public interface ShippingMethodCustom {
    Long getId();
    String getName();
    BigDecimal getPrice();
    String getDescription();
    String getCreatedBy();
    String getUpdatedBy();
    Date getCreatedAt();
    Date getUpdatedAt();
    Integer getStatus();
}
