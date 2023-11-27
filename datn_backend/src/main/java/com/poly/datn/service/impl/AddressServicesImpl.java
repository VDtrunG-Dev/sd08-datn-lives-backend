package com.poly.datn.service.impl;

import com.poly.datn.model.TAddress;
import com.poly.datn.repository.IAddressRepository;
import com.poly.datn.service.IAddressServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServicesImpl implements IAddressServices {

    @Autowired
    private IAddressRepository addressRepository;

    @Override
    public Page<TAddress> findAll(int pageNumber, String search) {
        return addressRepository.findAll(PageRequest.of(pageNumber,10),search);
    }

    @Override
    public String addAddress(TAddress address) {
        try {
            address.setStatus(1);
            addressRepository.save(address);
        }catch (Exception e){
            return "Thêm Thất Bại";
        }
        return "Thêm Thành Công";
    }

    @Override
    public String deleteAddressById(Long id) {
        TAddress address = addressRepository.findByIdAddress(id);

        if(address == null){
            return "Địa chỉ không tồn tại";
        }
        try{
            address.setStatus(0);
            addressRepository.save(address);
        }catch (Exception e){
            return "Xoá Thất Bại";
        }
        return "Xoá thành công";
    }

    @Override
    public String updateAddress(TAddress address) {
        TAddress addressfindById = addressRepository.findByIdAddress(address.getId());
        if(addressfindById == null){
            return "Địa chỉ không tồn tại";
        }
        addressfindById.setStatus(addressfindById.getStatus());
        addressfindById.setProvince(address.getProvince());
        addressfindById.setDistrict(address.getDistrict());
        addressfindById.setWard(address.getWard());
        addressfindById.setDetailAddress(address.getDetailAddress());
        addressRepository.save(address);
        return "Cập Nhập Thành Công";
    }

    @Override
    public TAddress findById(Long id) {
        return addressRepository.findByIdAddress(id);
    }

    @Override
    public List<TAddress> search(String keyword) {

        return addressRepository.findAll().stream().filter(
                address -> address.getDetailAddress().contains(keyword) ||
                        address.getProvince().contains(keyword) ||
                        address.getDistrict().contains(keyword) ||
                        address.getWard().contains(keyword)
        ).collect(Collectors.toList());
    }

}
