package com.spring.shop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ReturnExchange")
public class RetunExchange implements Serializable {
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
    @Column(name = "Image")
    private String Image;
    @Column(name = "Note")
    private String Note;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "RequestDate")
    private Date RequestDate;
    @Column(name = "ProcessingDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ProcessingDate;
    @Column(name = "IsReturnExchange")
    private Boolean IsReturnExchange;
    @ManyToOne
    @JoinColumn(name = "IdCustomer")
    private Customer customer;
    @OneToMany(mappedBy = "retunExchange")
    private Set<ReturnExchangeHistory> returnExchangeHistorys = new HashSet<ReturnExchangeHistory>();
    @OneToMany(mappedBy = "retunExchange")
    private Set<ReturnExchangeDetail> returnExchangeDetails = new HashSet<ReturnExchangeDetail>();

}
