package com.poly.datn.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProducDetailDTO {
    private String name;
    private BigDecimal price;
    private BigDecimal priceNow;
    private int quantity;
    private String avatar;
    private String description;
}
