package com.poly.datn.service;

import com.poly.datn.dto.BillDetailRequest;
import com.poly.datn.model.TBillDetail;
import com.poly.datn.model.TBillDetail;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BillDetailService {
    List<TBillDetail> getAllActice();

//    List<TBillDetail> getAll();

    Page<TBillDetail> PageGetAllBillDetails(Integer pageNo, Integer pageSize);

    Optional<TBillDetail> getBillDetailById(Long id);

    TBillDetail createBillDetail(BillDetailRequest request);

    TBillDetail updateBillDetail(Long id, BillDetailRequest request);

    void deleteBillDetail(Long id);
}
