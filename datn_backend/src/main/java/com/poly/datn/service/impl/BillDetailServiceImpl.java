package com.poly.datn.service.impl;

import com.poly.datn.dto.BillDetailRequest;
import com.poly.datn.model.TBillDetail;
import com.poly.datn.repository.BillDetailRepository;
import com.poly.datn.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillDetailServiceImpl implements BillDetailService {
    @Autowired
    private BillDetailRepository billDetailRepository;

    @Override
    public List<TBillDetail> getAllActice() {
        return billDetailRepository.getAllActive();
    }

//    @Override
//    public List<TBillDetail> getAll() {
//        return billDetailRepository.findAll();
//    }

    @Override
    public Page<TBillDetail> PageGetAllBillDetails(Integer pageNo, Integer pageSize) {
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        return billDetailRepository.PageGetAllBillDetails(pageable);
    }

    @Override
    public Optional<TBillDetail> getBillDetailById(Long id) {
        return billDetailRepository.findById(id);
    }

    @Override
    public TBillDetail createBillDetail(BillDetailRequest request) {
        //kiem tra co ten san pham chua
        TBillDetail paymentType;
        List<TBillDetail> foundPaymentypes = billDetailRepository.findByBillId(request.getBillId());
        if (foundPaymentypes.size() > 0) {
            // Da co Xử lý lỗi, ví dụ:
            throw new IllegalArgumentException("Product name already taken");
        } else {
            paymentType = request.dto(new TBillDetail());
        }

        // Chua co Tiến hành thêm sản phẩm
        return billDetailRepository.save(paymentType);
    }

    @Override
    public TBillDetail updateBillDetail(Long id, BillDetailRequest request) {
        Optional<TBillDetail> paymentTypeOptional = billDetailRepository.findById(id);
        if (paymentTypeOptional.isPresent()) {
            TBillDetail paymentType = paymentTypeOptional.get();
            paymentType = request.dto(paymentType);
            return billDetailRepository.save(paymentType);
        } else {
            return null;

        }
    }

    @Override
    public void deleteBillDetail(Long id) {
        Optional<TBillDetail> paymentTypeOptional = billDetailRepository.findById(id);
        if (paymentTypeOptional.isPresent()) {
            TBillDetail paymentType = paymentTypeOptional.get();
            paymentType.setStatus(0);
            billDetailRepository.save(paymentType);
        }
    }
}