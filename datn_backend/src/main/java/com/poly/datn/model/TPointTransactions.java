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
@Table(name = "t_point_transactions")
public class TPointTransactions {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private TUser customer;

    @Column(name = "transaction_type")
    private Integer transactionType;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;
}
