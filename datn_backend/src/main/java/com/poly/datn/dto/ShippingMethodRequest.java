package com.poly.datn.dto;

import com.poly.datn.model.TShippingMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class ShippingMethodRequest {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String createdBy;
    private String updatedBy;
    private Date createdAt;
    private Date updatedAt;
    private Integer status;

    public TShippingMethod dto(TShippingMethod sMethod){
        sMethod.setId(this.getId());
        sMethod.setName(this.getName());
        sMethod.setPrice(this.getPrice());
        sMethod.setDescription(this.getDescription());
        sMethod.setCreatedBy(this.getCreatedBy());
        sMethod.setUpdatedBy(this.getUpdatedBy());
        sMethod.setCreatedAt(this.getCreatedAt());
        sMethod.setUpdatedAt(this.getUpdatedAt());
        sMethod.setStatus(this.getStatus());
        return sMethod;
    }
}
