package com.poly.datn.dto;

import com.poly.datn.model.TBill;
import com.poly.datn.model.TBillDetail;
import com.poly.datn.model.TPaymentType;
import com.poly.datn.model.TProductVariation;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class BillDetailRequest {
    private Long id;

    private Long billId;

    private Long productVariation;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal discount;

    private BigDecimal tax;

    private BigDecimal unitPrice;

    private BigDecimal subtotalPrice;

    private Integer status;

    public TBillDetail dto(TBillDetail billDetail){
        billDetail.setId(this.getId());
        billDetail.setBill(TBill.builder().id(Long.valueOf(this.getBillId())).build());
        billDetail.setProductVariation(TProductVariation.builder().id(Long.valueOf(this.getProductVariation())).build());
        billDetail.setQuantity(this.getQuantity());
        billDetail.setPrice(this.getPrice());
        billDetail.setDiscount(this.getDiscount());
        billDetail.setTax(this.getTax());
        billDetail.setUnitPrice(this.getUnitPrice());
        billDetail.setSubtotalPrice(this.getSubtotalPrice());
        billDetail.setStatus(this.getStatus());
        return billDetail;
    }
}
