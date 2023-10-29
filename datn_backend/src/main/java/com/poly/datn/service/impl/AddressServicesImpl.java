package com.poly.datn.service.impl;

import com.poly.datn.model.TAddress;
import com.poly.datn.repository.IAddressRepository;
import com.poly.datn.service.IAddressServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServicesImpl implements IAddressServices {

    @Autowired
    private IAddressRepository addressRepository;

    @Override
    public Page<TAddress> findAllPage(int pageNumber) {
        return addressRepository.findAll(PageRequest.of(pageNumber,5));
    }


    @Override
    public List<TAddress> findAll(){
        return addressRepository.findAll();
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
            addressRepository.delete(address);
        }catch (Exception e){
            return "Xoá Thất Bại";
        }
        return "Xoá thành công";
    }

    @Override
    public String updateAddress(TAddress address) {
        addressRepository.save(address);
        return "Cập Nhập Thành Công";
    }

    @Override
    public List<TAddress> findByKeywork(String keyword) {
        List<TAddress> addressfindAll = addressRepository.findAll();

        return addressfindAll
                .stream().filter(
                        address -> address.getProvince().contains(keyword) ||
                                address.getDistrict().contains(keyword) ||
                                address.getWard().contains(keyword) ||
                                address.getDetailAddress().contains(keyword)
                ).collect(Collectors.toList());
    }

    @Override
    public TAddress findById(Long id) {
        return addressRepository.findByIdAddress(id);
    }

    @Override
    public List<TAddress> findByStatus0() {
        return addressRepository.findByStatus0();
    }

}
