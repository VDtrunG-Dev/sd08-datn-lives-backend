package com.poly.datn.dto;

import com.poly.datn.model.TPaymentType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class PaymentTypeRequest {
    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private Integer status;

    public TPaymentType dto(TPaymentType paymentType){
        paymentType.setId(this.getId());
        paymentType.setName(this.getName());
        paymentType.setCreatedAt(this.getCreatedAt());
        paymentType.setUpdatedAt(this.getUpdatedAt());
        paymentType.setStatus(this.getStatus());
        return paymentType;
    }
}
