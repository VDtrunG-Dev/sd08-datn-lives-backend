package com.spring.shop.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBillStatus {
    private String Code;
    private Integer Status;
}
