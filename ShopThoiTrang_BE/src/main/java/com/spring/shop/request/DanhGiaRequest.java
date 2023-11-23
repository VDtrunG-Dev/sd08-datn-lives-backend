package com.spring.shop.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class DanhGiaRequest {
    private Integer Score;
    private String Note;
    private Integer IdProductDetail;
    private Integer IdCustomer;
}
