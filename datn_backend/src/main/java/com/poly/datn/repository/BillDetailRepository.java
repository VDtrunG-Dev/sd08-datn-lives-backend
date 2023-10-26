package com.poly.datn.repository;

import com.poly.datn.model.TBillDetail;
import com.poly.datn.model.TBillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<TBillDetail,Long> {
    @Query(value = "select id,name,created_at,updated_at,status from t_payment_type where status=1",nativeQuery = true)
    List<TBillDetail> getAllActive();

    List<TBillDetail> findByBillId(Long billId);

    @Query(value = "select id,name,created_at,updated_at,status from t_payment_type where status=1",nativeQuery = true)
    Page<TBillDetail> PageGetAllBillDetails(Pageable pageable);
}
