package com.poly.datn.service.impl;

import com.poly.datn.model.TBill;
import com.poly.datn.repository.IBillRepository;
import com.poly.datn.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Override
    public List<TBill> searchAll(String customer, String shippingMethod, BigDecimal cash) {
        return billRepository.findAll().stream()
                .filter(billByName -> billByName.getCustomer().getLastName().contains(customer))
                .filter(billByName -> billByName.getShippingMethod().getName().contains(shippingMethod))
                .filter(billByName -> billByName.getCash().equals(cash))
                .collect(Collectors.toList());
    }

    @Override
    public List<TBill> searchByKeyword(String keyword) {
        return billRepository.findAll().stream()
                .filter(bill -> bill.getCustomer().getLastName().contains(keyword)
                        || bill.getShippingMethod().getName().contains(keyword)
                        || bill.getCash().equals(keyword))
                .collect(Collectors.toList());
    }
}
