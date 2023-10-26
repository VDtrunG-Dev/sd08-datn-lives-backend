package com.poly.datn.repository;

import com.poly.datn.model.TPaymentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentTypeRepository extends JpaRepository<TPaymentType,Long> {
    @Query(value = "select id,name,created_at,updated_at,status from t_payment_type where status=1",nativeQuery = true)
    List<TPaymentType> getAllActive();

    @Query(value = "select id,name,created_at,updated_at,status from t_payment_type where status=0",nativeQuery = true)
    List<TPaymentType> getAllDeleted();

    List<TPaymentType> findByName(String paymenTypeName);

    @Query(value = "select id,name,created_at,updated_at,status from t_payment_type where status=1",nativeQuery = true)
    Page<TPaymentType> PageGetAllPaymenttypes(Pageable pageable);

    @Query(value = "select id,name,created_at,updated_at,status from t_payment_type where status=0",nativeQuery = true)
    Page<TPaymentType> PageGetAllPaymenttypesDeleted(Pageable pageable);

}
