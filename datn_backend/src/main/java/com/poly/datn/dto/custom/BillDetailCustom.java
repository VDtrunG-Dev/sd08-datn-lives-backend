package com.poly.datn.dto.custom;

import com.poly.datn.model.TBill;
import com.poly.datn.model.TProductVariation;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

public interface BillDetailCustom {
    Long getid();

    Long getbill();

    Long getproductVariation();

    Integer getquantity();

    BigDecimal getprice();

    BigDecimal getdiscount();

    BigDecimal gettax();

    BigDecimal getunitPrice();

    BigDecimal getsubtotalPrice();

    Integer getstatus();
}
