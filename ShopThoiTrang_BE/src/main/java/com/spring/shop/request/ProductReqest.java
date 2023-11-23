package com.spring.shop.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ProductReqest {
    private String Code;
    private String Name;
    private String Description;
    private String CreateBy;
    private String UpdateBy;
}
