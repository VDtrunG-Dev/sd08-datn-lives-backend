package com.poly.datn.service.impl;

import com.poly.datn.model.TBill;
import com.poly.datn.repository.IBillRepository;
import com.poly.datn.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BillServiceImpl implements IBillService {
    @Autowired
    private IBillRepository billRepository;

    @Override
    public List<TBill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public TBill updateBillStatus(Long id, Integer status) {
        return billRepository.findById(id)
                .map(existingBill -> {
                    existingBill.setStatus(status);
                    return billRepository.save(existingBill);
                })
                .orElse(null);
    }
}
