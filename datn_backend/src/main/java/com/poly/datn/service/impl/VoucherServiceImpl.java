package com.poly.datn.service.impl;

import com.poly.datn.model.TVoucher;
import com.poly.datn.repository.IVoucherRepository;
import com.poly.datn.service.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


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
    public boolean deleteVoucher(Long id) {
        if (voucherRepository.existsById(id)) {
            voucherRepository.deleteById(id);
            return true;
        }
        return false;

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


}
