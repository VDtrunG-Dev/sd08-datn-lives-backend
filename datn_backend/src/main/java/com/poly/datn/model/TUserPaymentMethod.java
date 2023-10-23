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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "t_user_payment_method")
public class TUserPaymentMethod {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private TUser customer;

    @ManyToOne
    @JoinColumn(name = "payment_type_id")
    private TPaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private TBill bill;

    @Column(name = "provider")
    private String provider;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "status")
    private Integer status;

}
