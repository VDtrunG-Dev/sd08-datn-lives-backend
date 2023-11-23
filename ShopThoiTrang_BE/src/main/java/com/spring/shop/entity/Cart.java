package com.spring.shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Cart")
public class Cart implements Serializable {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer Id;
    @OneToOne
    @JoinColumn(name = "IdCustomer")
    private Customer customer;
    @OneToMany(mappedBy = "cart")
    private Set<CartDetail> cartDetails = new HashSet<CartDetail>();
}
