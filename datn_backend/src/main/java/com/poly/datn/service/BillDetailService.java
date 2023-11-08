package com.poly.datn.service;

import com.poly.datn.dto.BillDetailRequest;
import com.poly.datn.model.TBillDetail;
import com.poly.datn.model.TBillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

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




    Page<TBillDetail> PageGetAllBillDetailsDaThanhToan(Pageable pageable);

    Page<TBillDetail> PageGetAllBillDetailsDaHuy(Pageable pageable);

    Page<TBillDetail> PageGetAllBillDetailsVanChuyen(Pageable pageable);

    Page<TBillDetail> PageGetAllBillDetailsDangHoan(Pageable pageable);

    Page<TBillDetail> PageGetAllBillDetailsDaHoanKiemTra(Pageable pageable);

    Page<TBillDetail> PageGetAllBillDetailsDaTraHoanTien(Pageable pageable);

    Page<TBillDetail> PageGetAllBillDetailsDoiTra(Pageable pageable);
}
