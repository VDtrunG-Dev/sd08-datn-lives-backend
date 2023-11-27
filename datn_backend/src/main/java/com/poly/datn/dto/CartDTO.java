package com.poly.datn.dto;

import com.poly.datn.model.TProductVariation;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CartDTO {

    private Long id;
    private int quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private String productVariationsName;
}
