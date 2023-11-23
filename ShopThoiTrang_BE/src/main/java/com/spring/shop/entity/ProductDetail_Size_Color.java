package com.spring.shop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ProductDetail_Color_Size")
public class ProductDetail_Size_Color implements Serializable {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer Id;
    @ManyToOne
    @JoinColumn(name = "IdProductDetail")
    @JsonBackReference
    private ProductDetail productDetail;
    @ManyToOne
    @JoinColumn(name = "IdSize")
    private Size size;
    @ManyToOne
    @JoinColumn(name = "IdColor")
    private Color color;
    @Column(name = "Quantity")
    private Integer Quantity;

}
