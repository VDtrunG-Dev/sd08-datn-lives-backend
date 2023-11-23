package com.spring.shop.request;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetail_Size_ColorRequest {
    private Integer IdProductDetail;
    private Integer IdSize;
    private Integer IdColor;
    private Integer Quantity;
}
