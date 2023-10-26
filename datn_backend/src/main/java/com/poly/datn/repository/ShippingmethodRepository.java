package com.poly.datn.repository;

import com.poly.datn.model.TPaymentType;
import com.poly.datn.model.TShippingMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingmethodRepository extends JpaRepository<TShippingMethod,Long> {
    @Query(value = "select id,name,price,description,created_by,updated_by,created_at,updated_at,status from t_shipping_method where status=1",nativeQuery = true)
    List<TShippingMethod> getAllActive();

    List<TShippingMethod> findByName(String smethodName);

    @Query(value = "select id,name,price,description,created_by,updated_by,created_at,updated_at,status from t_shipping_method where status=1",nativeQuery = true)
    Page<TShippingMethod> PageGetAllSmethods(Pageable pageable);
}
