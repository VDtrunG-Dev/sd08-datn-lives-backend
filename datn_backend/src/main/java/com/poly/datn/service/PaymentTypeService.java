package com.poly.datn.service;

import com.poly.datn.dto.custom.PaymentTypeCustom;
import com.poly.datn.dto.request.PaymentTypeRequest;
import com.poly.datn.model.TPaymentType;
import com.poly.datn.model.TProduct;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PaymentTypeService {
    List<TPaymentType> getAllActice();
    List<TPaymentType> getAll();
    //

    Page<TPaymentType> PageGetAllPaymenttypes(Integer pageNo, Integer pageSize);

    Optional<TPaymentType> getPaymentTypeById(Long id);

    TPaymentType createPaymentType(PaymentTypeRequest request);

    TPaymentType updatePaymentType(Long id, PaymentTypeRequest request);

    void deletePaymentType(Long id);
}
