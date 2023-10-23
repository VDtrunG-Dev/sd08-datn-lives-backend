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
@Table(name = "t_shopping_cart_detail")
public class TShoppingCartDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private TShoppingCart cart;

    @ManyToOne
    @JoinColumn(name = "product_variation_id")
    private TProductVariation productVariation;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "status")
    private Integer status;
}
