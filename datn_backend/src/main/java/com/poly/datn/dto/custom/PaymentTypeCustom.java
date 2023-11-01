package com.poly.datn.dto.custom;

import java.sql.Date;

public interface PaymentTypeCustom {
    Long getId();
    String getName();
    Date getCreatedAt();
    Date getUpdatedAt();
    Integer getStatus();
}
