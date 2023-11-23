package com.spring.shop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customer")
@Builder
public class Customer implements Serializable {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer Id;
    @Column(name = "CreateDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreateDate;
    @Column(name = "UpdateDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date UpdateDate;
    @Column(name = "CreateBy")
    private String CreateBy;
    @Column(name = "UpdateBy")
    private String UpdateBy;
    @Column(name = "Status")
    private Integer Status;
    @Column(name = "Code")
    private String Code;
    @Column(name = "Fullname")
    private String Fullname;
    @Column(name = "Username")
    private String Username;
    @Column(name = "Password")
    private String Password;
    @Column(name = "Image")
    private String Image;
    @Column(name = "Gender")
    private Boolean Gender;
    @Column(name = "Phone")
    private String Phone;
    @Column(name = "Email")
    private String Email;
    @JsonBackReference
    @OneToMany(mappedBy = "customer")
    private Set<Coupon> coupons = new HashSet<Coupon>();
    @JsonBackReference
    @OneToMany(mappedBy = "customer")
    private Set<Address> addresses = new HashSet<Address>();
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Bill> bills = new HashSet<Bill>();
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Rating> ratings = new HashSet<Rating>();
    @JsonBackReference
    @OneToMany(mappedBy = "customer")
    private Set<RetunExchange> retunExchanges = new HashSet<RetunExchange>();

}
