package com.spring.shop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ReturnExchangeDetail")
public class ReturnExchangeDetail implements Serializable{
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer Id;
    @ManyToOne
    @JoinColumn(name = "IdReturnExchange")
    @JsonBackReference
    private RetunExchange retunExchange;
    @ManyToOne
    @JoinColumn(name = "IdOrderDetail")
    @JsonBackReference
    private BillDetail billDetail;
}
