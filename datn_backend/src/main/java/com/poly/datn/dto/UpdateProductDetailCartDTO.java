package com.poly.datn.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDetailCartDTO {
    private Long idCartDetail;
    private String productDetailName;
    private int quantity;
}
