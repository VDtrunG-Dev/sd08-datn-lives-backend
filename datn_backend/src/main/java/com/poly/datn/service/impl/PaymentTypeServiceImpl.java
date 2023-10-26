package com.poly.datn.service.impl;

import com.poly.datn.dto.PaymentTypeRequest;
import com.poly.datn.model.TPaymentType;
import com.poly.datn.repository.PaymentTypeRepository;
import com.poly.datn.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Override
    public List<TPaymentType> getAllActice() {
        return paymentTypeRepository.getAllActive();
    }

//    @Override
//    public List<TPaymentType> getAll() {
//        return paymentTypeRepository.findAll();
//    }

    @Override
    public Page<TPaymentType> PageGetAllPaymenttypes(Integer pageNo, Integer pageSize) {
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        return paymentTypeRepository.PageGetAllPaymenttypes(pageable);
    }

    @Override
    public Optional<TPaymentType> getPaymentTypeById(Long id) {
        return paymentTypeRepository.findById(id);
    }

    @Override
    public TPaymentType createPaymentType(PaymentTypeRequest request) {
        //kiem tra co ten san pham chua
        TPaymentType paymentType;
        List<TPaymentType> foundPaymentypes = paymentTypeRepository.findByName(request.getName().trim());
        if (foundPaymentypes.size() > 0) {
            // Da co Xử lý lỗi, ví dụ:
            throw new IllegalArgumentException("Product name already taken");
        } else {
            paymentType = request.dto(new TPaymentType());
        }

        // Chua co Tiến hành thêm sản phẩm
        return paymentTypeRepository.save(paymentType);
    }

    @Override
    public TPaymentType updatePaymentType(Long id, PaymentTypeRequest request) {
        Optional<TPaymentType> paymentTypeOptional = paymentTypeRepository.findById(id);
        if (paymentTypeOptional.isPresent()) {
            TPaymentType paymentType = paymentTypeOptional.get();
            paymentType = request.dto(paymentType);
            return paymentTypeRepository.save(paymentType);
        } else {
            return null;

        }
    }

    @Override
    public void deletePaymentType(Long id) {
        Optional<TPaymentType> paymentTypeOptional = paymentTypeRepository.findById(id);
        if (paymentTypeOptional.isPresent()) {
            TPaymentType paymentType = paymentTypeOptional.get();
            paymentType.setStatus(0);
            paymentTypeRepository.save(paymentType);
        }
    }
}
