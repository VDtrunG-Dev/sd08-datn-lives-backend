package com.poly.datn.service;

import com.poly.datn.model.TBill;

import java.util.List;

public interface IBillService {
    List<TBill> getAllBills();

    TBill updateBillStatus(Long id, Integer status);
}
