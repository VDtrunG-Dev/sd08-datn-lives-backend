package com.poly.datn.service;

import com.poly.datn.dto.ShippingMethodRequest;
import com.poly.datn.model.TPaymentType;
import com.poly.datn.model.TShippingMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ShippingmethodService {
    List<TShippingMethod> getAllActice();

    List<TShippingMethod> getAllDeleted();

//    List<TShippingMethod> getAll();

    Page<TShippingMethod> PageGetAllTShippingMethods(Integer pageNo, Integer pageSize);

    Page<TShippingMethod> PageGetAllDeletedSmethods(Integer pageNo, Integer pageSize);

    Optional<TShippingMethod> getTShippingMethodById(Long id);

    TShippingMethod createTShippingMethod(ShippingMethodRequest request);

    TShippingMethod updateTShippingMethod(Long id, ShippingMethodRequest request);

    TShippingMethod updateTShippingMethodActive(Long id);

    void deleteTShippingMethod(Long id);

    List<TShippingMethod> searchAllKeyWord(String keyWord);

    Boolean checkEmpty(ShippingMethodRequest request);
    String messageValidate(ShippingMethodRequest request);
    Boolean checkExist(ShippingMethodRequest request);

}
