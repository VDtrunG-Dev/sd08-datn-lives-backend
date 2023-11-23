package com.spring.shop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CartDetail")
public class CartDetail implements Serializable {
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
    @ManyToOne
    @JoinColumn(name = "IdCart")
    @JsonBackReference
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "IdProductDetail")
    private ProductDetail productDetail;
}
