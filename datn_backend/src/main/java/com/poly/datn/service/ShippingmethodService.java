package com.poly.datn.service;

import com.poly.datn.dto.ShippingMethodRequest;
import com.poly.datn.model.TPaymentType;
import com.poly.datn.model.TShippingMethod;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ShippingmethodService {
    List<TShippingMethod> getAllActice();

//    List<TShippingMethod> getAll();

    Page<TShippingMethod> PageGetAllTShippingMethods(Integer pageNo, Integer pageSize);

    Optional<TShippingMethod> getTShippingMethodById(Long id);

    TShippingMethod createTShippingMethod(ShippingMethodRequest request);

    TShippingMethod updateTShippingMethod(Long id, ShippingMethodRequest request);

    void deleteTShippingMethod(Long id);
}
