package com.poly.datn.service;

import com.poly.datn.model.TBill;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IBillService {
    List<TBill> getAllBills();

    TBill updateBillStatus(Long id, Integer status);

    Page<TBill> getAll(Integer page);

    Page<TBill> getActiveBills(Integer status, Integer page);

    Page<TBill> getInactiveBills(Integer status, Integer page);

    public Optional<TBill> getRoleById(Long id);

    List<TBill> searchAll(String customer, String shippingMethod, BigDecimal cash);
    List<TBill> searchByKeyword(String keyword);
}
