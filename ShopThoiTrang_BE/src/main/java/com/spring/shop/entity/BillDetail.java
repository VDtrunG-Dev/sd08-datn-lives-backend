package com.spring.shop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "BillDetail")
public class BillDetail implements Serializable {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer Id;
    @Column(name = "UnitPrice")
    private BigDecimal UnitPrice;
    @Column(name = "Quantity")
    private Integer Quantity;
    @Column(name = "IdColor")
    private Integer IdColor;
    @Column(name = "IdSize")
    private Integer IdSize;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "IdOrder")
    private Bill bill;
    @ManyToOne
    @JoinColumn(name = "IdProductDetail")
    private ProductDetail productDetail;
    @JsonIgnore
    @OneToMany(mappedBy = "billDetail")
    private Set<ReturnExchangeDetail> returnExchangeDetails = new HashSet<ReturnExchangeDetail>();
}
