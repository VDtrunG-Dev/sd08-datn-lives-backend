package com.poly.datn.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity

@Table(name = "t_address")
public class TAddress {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
