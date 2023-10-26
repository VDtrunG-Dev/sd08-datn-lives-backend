package com.poly.datn.service.impl;

import com.poly.datn.dto.ShippingMethodRequest;
import com.poly.datn.model.TShippingMethod;
import com.poly.datn.repository.ShippingmethodRepository;
import com.poly.datn.service.ShippingmethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingmethodServiceImpl implements ShippingmethodService {
    @Autowired
    private ShippingmethodRepository shippingmethodRepository;

    @Override
    public List<TShippingMethod> getAllActice() {
        return shippingmethodRepository.getAllActive();
    }

    @Override
    public Page<TShippingMethod> PageGetAllTShippingMethods(Integer pageNo, Integer pageSize) {
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        return shippingmethodRepository.PageGetAllSmethods(pageable);
    }

    @Override
    public Optional<TShippingMethod> getTShippingMethodById(Long id) {
        return shippingmethodRepository.findById(id);
    }

    @Override
    public TShippingMethod createTShippingMethod(ShippingMethodRequest request) {
        //kiem tra co ten san pham chua
        TShippingMethod smethod;
        List<TShippingMethod> foundSmethod = shippingmethodRepository.findByName(request.getName().trim());
        if (foundSmethod.size() > 0) {
            // Da co Xử lý lỗi, ví dụ:
            throw new IllegalArgumentException("This name already taken");
        } else {
            smethod = request.dto(new TShippingMethod());
        }

        // Chua co Tiến hành thêm sản phẩm
        return shippingmethodRepository.save(smethod);
    }

    @Override
    public TShippingMethod updateTShippingMethod(Long id, ShippingMethodRequest request) {
        Optional<TShippingMethod> sMethodOptional = shippingmethodRepository.findById(id);
        if (sMethodOptional.isPresent()) {
            TShippingMethod sMethod = sMethodOptional.get();
            sMethod = request.dto(sMethod);
            return shippingmethodRepository.save(sMethod);
        } else {
            return null;

        }
    }

    @Override
    public void deleteTShippingMethod(Long id) {
        Optional<TShippingMethod> sMethodOptional = shippingmethodRepository.findById(id);
        if (sMethodOptional.isPresent()) {
            TShippingMethod sMethod = sMethodOptional.get();
            sMethod.setStatus(0);
            shippingmethodRepository.save(sMethod);
        }
    }
}
