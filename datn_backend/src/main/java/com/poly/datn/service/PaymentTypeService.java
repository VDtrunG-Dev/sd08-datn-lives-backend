package com.poly.datn.service;

import com.poly.datn.dto.PaymentTypeRequest;
import com.poly.datn.model.TPaymentType;
import com.poly.datn.model.TRole;
import org.springframework.data.domain.Page;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface PaymentTypeService {
    List<TPaymentType> getAllActice();

    List<TPaymentType> getAllDeleted();
//    List<TPaymentType> getAll();

    Page<TPaymentType> PageGetAllPaymenttypes(Integer pageNo, Integer pageSize);

    Page<TPaymentType> PageGetAllPaymenttypesDeleted(Integer pageNo, Integer pageSize);

    Optional<TPaymentType> getPaymentTypeById(Long id);

    TPaymentType createPaymentType(PaymentTypeRequest request);

    TPaymentType updatePaymentType(Long id, PaymentTypeRequest request);

    TPaymentType updatePaymentTypeActive(Long id);

    void deletePaymentType(Long id);

    List<TPaymentType> searchAll(String namePayment);

    List<TPaymentType> searchByKeyword(String keyword);
}
