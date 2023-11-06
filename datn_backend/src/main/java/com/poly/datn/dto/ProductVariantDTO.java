package com.poly.datn.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductVariantDTO {
    private String productName;
    private List<String> options;
    private int quantity;
    private BigDecimal price;
}
