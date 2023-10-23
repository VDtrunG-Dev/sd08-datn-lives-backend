package com.poly.datn.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "t_bill")
public class TBill {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private TUser customer;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private TVoucher voucher;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private TUser staff;

    @ManyToOne
    @JoinColumn(name = "shipping_method_id")
    private TShippingMethod shippingMethod;

    @Column(name = "bill_code")
    private String billCode;

    @Column(name = "recipient_phone")
    private String recipientPhone;

    @Column(name = "cash")
    private BigDecimal cash;

    @Column(name = "money_transfer")
    private BigDecimal moneyTransfer;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "total_amount_after_discount")
    private BigDecimal totalAmountAfterDiscount;

    @Column(name = "province")
    private String province;

    @Column(name = "district")
    private String district;

    @Column(name = "ward")
    private String ward;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false)
    private Date updatedAt;

    @Column(name = "status")
    private Integer status;

}
