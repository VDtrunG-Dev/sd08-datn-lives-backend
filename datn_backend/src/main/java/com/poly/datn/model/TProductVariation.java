package com.poly.datn.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "t_product_variation")
public class TProductVariation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id")
    private TProduct product;

    @OneToMany(mappedBy = "productVariation", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TShoppingCartDetail> cartDetails;

    @OneToMany(mappedBy = "productVariation", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TBillDetail> billDetails;


    @Column(name = "sku")
    private String sku;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "price_now")
    private BigDecimal priceNow;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "view")
    private Integer view;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "description")
    private String description;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_by", insertable = false)
    private String updatedBy;

    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(name = "status",insertable = false, columnDefinition = "INTEGER DEFAULT 1")
    private Integer status;

}
