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

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShippingmethodServiceImpl implements ShippingmethodService {
    @Autowired
    private ShippingmethodRepository shippingmethodRepository;

    @Override
    public List<TShippingMethod> getAllActice() {
        return shippingmethodRepository.getAllActive();
    }

    @Override
    public List<TShippingMethod> getAllDeleted() {
        return shippingmethodRepository.getAllDeleted();
    }

    @Override
    public Page<TShippingMethod> PageGetAllTShippingMethods(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return shippingmethodRepository.PageGetAllSmethods(pageable);
    }

    @Override
    public Page<TShippingMethod> PageGetAllDeletedSmethods(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return shippingmethodRepository.PageGetAllDeletedSmethods(pageable);
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
        if (!checkEmpty(request)) {
            // Da co Xử lý lỗi, ví dụ:
            throw new IllegalArgumentException(messageValidate(request));
        } else if (!checkExist(request)) {
            throw new IllegalArgumentException(messageValidate(request));
        } else {
            smethod = request.dto(new TShippingMethod());
        }

        // Chua co Tiến hành thêm sản phẩm
        smethod.setCreatedAt(getCurrentDate());
        smethod.setUpdatedAt(getCurrentDate());
        return shippingmethodRepository.save(smethod);
    }

    @Override
    public TShippingMethod updateTShippingMethod(Long id, ShippingMethodRequest request) {
        Optional<TShippingMethod> sMethodOptional = shippingmethodRepository.findById(id);
        if (sMethodOptional.isPresent() && checkEmpty(request) ) {
            TShippingMethod sMethod = sMethodOptional.get();
            sMethod = request.dto(sMethod);
            sMethod.setUpdatedAt(getCurrentDate());
            return shippingmethodRepository.save(sMethod);
        } else {
            return null;
        }
    }

    @Override
    public TShippingMethod updateTShippingMethodActive(Long id) {
        Optional<TShippingMethod> sMethodOptional = shippingmethodRepository.findById(id);
        if (sMethodOptional.isPresent() ) {
            TShippingMethod sMethod = sMethodOptional.get();
            sMethod.setStatus(1);
            sMethod.setUpdatedAt(getCurrentDate());
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
            sMethod.setUpdatedAt(getCurrentDate());
            shippingmethodRepository.save(sMethod);
        }
    }

    @Override
    public List<TShippingMethod> searchAllKeyWord(String keyWord) {
        return shippingmethodRepository.findAll().stream()
                .filter(tShippingMethod -> tShippingMethod.getName().contains(keyWord)
                                || tShippingMethod.getDescription().contains(keyWord)
//                        || tShippingMethod.getCreatedBy().contains(keyWord)
//                        ||tShippingMethod.getUpdatedBy().contains(keyWord)
                )
                .collect(Collectors.toList());
    }

    @Override
    public Boolean checkEmpty(ShippingMethodRequest request) {
        if (request.getName().length() <= 0 || request.getPrice().toString().length() <= 0
                || request.getDescription().length() <= 0) {
            return false;
        } else
            return true;
    }

    @Override
    public Boolean checkExist(ShippingMethodRequest request) {
        if (!searchAllKeyWord(request.getName()).isEmpty()) {
            return false;
        } else return true;
    }

    @Override
    public List<TShippingMethod> searchTShippingMethodBySearchTerm(String searchTerm) {
        return shippingmethodRepository.searchTShippingMethodBySearchTerm(searchTerm);
    }

    @Override
    public String messageValidate(ShippingMethodRequest request) {
        if (!checkEmpty(request)) {
            return "Không bỏ trống dữ liệu";
        } else if (!checkExist(request)) {
            return "Đã có phương thức vận chuyển này vui lòng đổi tên khác";
        } else
            return "Không còn lỗi dữ liệu";
    }
    public Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date(calendar.getTimeInMillis());
        return currentDate;
    }

}
