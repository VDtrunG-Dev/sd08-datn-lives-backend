package com.poly.datn.service.impl;

import com.poly.datn.model.TBill;
import com.poly.datn.model.TRole;
import com.poly.datn.repository.IBillRepository;
import com.poly.datn.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BillServiceImpl implements IBillService {
    @Autowired
    private IBillRepository billRepository;

    public Page<TBill> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        return billRepository.findAll(pageable);
    }

    @Override
    public Page<TBill> getActiveBills(Integer status, Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        return billRepository.findByStatus(status, pageable);
    }

    @Override
    public Page<TBill> getInactiveBills(Integer status, Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        return billRepository.findByStatus(status, pageable);
    }

    @Override
    public Optional<TBill> getRoleById(Long id) {
        return billRepository.findById(id);
    }


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
