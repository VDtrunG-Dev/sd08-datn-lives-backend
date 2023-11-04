package com.poly.datn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String productName;
    private List<OptionDTO> option;
    private Integer quantity;
    private BigDecimal priceNow;
    private BigDecimal price;
    private String avatar;
    private String description;
}
