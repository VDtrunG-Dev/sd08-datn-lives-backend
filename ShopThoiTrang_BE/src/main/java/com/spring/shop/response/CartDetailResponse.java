package com.spring.shop.response;

import java.math.BigDecimal;

public interface CartDetailResponse {
    Integer getId();
    Integer getIdColor();
    Integer getIdSize();
    BigDecimal getUnitPrice();
    Integer getQuantity();
    Integer getIdProduct();
    Integer getIdCart();
}
