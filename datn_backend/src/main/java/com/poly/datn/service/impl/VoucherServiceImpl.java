package com.poly.datn.service.impl;

import com.poly.datn.model.TVoucher;
import com.poly.datn.repository.IVoucherRepository;
import com.poly.datn.service.IVoucherService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class VoucherServiceImpl implements IVoucherService {

    @Autowired
    private IVoucherRepository voucherRepository;

    @Override
    public List<TVoucher> getAllVoucher() {
        return voucherRepository.findAll();
    }

    @Override
    public Page<TVoucher> getAllPaged(int page, int size) {
        return voucherRepository.findAll(PageRequest.of(page, size));
    }


    @Override
    public Optional<TVoucher> getVoucherById(Long id) {
        return voucherRepository.findById(id);
    }

    @Override
    public TVoucher addVoucher(TVoucher tVoucher) {
        return voucherRepository.save(tVoucher);
    }


    @Override
    public TVoucher updateVoucher(Long id, TVoucher updatedVoucher) {
        if (voucherRepository.existsById(id)) {
            updatedVoucher.setId(id);
            return voucherRepository.save(updatedVoucher);
        }
        return null;
    }

    @Override
    public void deleteVoucher(Long id) {
        voucherRepository.findById(id).ifPresent(voucher -> voucherRepository.delete(voucher));
    }

    @Override
    public List<TVoucher> getAllVouchersByStatus(int status) {
        return voucherRepository.findByStatus(status);
    }

    @Override
    public Page<TVoucher> getAllByStatusPaged(int status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return voucherRepository.findByStatus(status, pageable);
    }

    @Override
    public List<TVoucher> searchAll(String voucherCode, Integer quantity, BigDecimal maximumCostReduction) {
        return voucherRepository.findAll().stream()
                .filter(voucher -> voucher.getVoucherCode().contains(voucherCode))
                .filter(voucher -> voucher.getQuantity().equals(quantity))
                .filter(voucher -> voucher.getMaximumCostReduction().equals(maximumCostReduction))
                .collect(Collectors.toList());
    }

    @Override
    public List<TVoucher> searchByKeyword(String keyword) {
        return voucherRepository.findAll().stream()
                .filter(voucher -> voucher.getVoucherCode().contains(keyword)
                        || voucher.getQuantity().toString().contains(keyword)
                        || voucher.getMaximumCostReduction().toString().contains(keyword))
                .collect(Collectors.toList());
    }


}
