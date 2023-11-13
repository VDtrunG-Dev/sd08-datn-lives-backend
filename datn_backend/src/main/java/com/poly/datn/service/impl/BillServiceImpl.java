package com.poly.datn.service.impl;

import com.poly.datn.dto.BillRequest;
import com.poly.datn.model.TBill;
import com.poly.datn.model.TVoucher;
import com.poly.datn.repository.IBillRepository;
import com.poly.datn.repository.IVoucherRepository;
import com.poly.datn.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BillServiceImpl implements IBillService {
    @Autowired
    private IBillRepository billRepository;

    @Autowired
    private IVoucherRepository voucherRepository;

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





    @Override
    public void checkVoucherBill(TBill billRequest) {
        BigDecimal minimumOrderBill = billRequest.getTotalAmount();
        TVoucher maxVoucher = null; // Biến để theo dõi voucher lớn nhất
        List<TVoucher> listVoucher = voucherRepository.findByStatus(1);
        for (int i = 0; i < listVoucher.size(); i++) {
            TVoucher currentVoucher = listVoucher.get(i);
            if (currentVoucher.getMinimumOrder().compareTo(minimumOrderBill) < 0) {
                if (maxVoucher == null || currentVoucher.getMinimumOrder().compareTo(maxVoucher.getMinimumOrder()) > 0) {
                    maxVoucher = currentVoucher; // Cập nhật voucher lớn nhất
                }
            }
        }

        if (maxVoucher != null) {
            // Sử dụng maxVoucher sau khi tìm thấy
            billRequest.setVoucher(maxVoucher);
            System.out.println("Voucher lớn nhất phù hợp: " + maxVoucher);
        } else {
            // Không tìm thấy voucher phù hợp
            System.out.println("Không tìm thấy voucher phù hợp.");
        }
    }

    @Override
    public TBill updateVoucher(Long id, BillRequest billRequest) {
        Optional<TBill> tBillOptional=billRepository.findById(id);
        if(tBillOptional.isPresent()){
            TBill tBill=tBillOptional.get();
            BigDecimal minimumOrderBill = tBill.getTotalAmount();
            TVoucher maxVoucher = null; // Biến để theo dõi voucher lớn nhất
            List<TVoucher> listVoucher = voucherRepository.findByStatus(1);
            for (int i = 0; i < listVoucher.size(); i++) {
                TVoucher currentVoucher = listVoucher.get(i);
                if (currentVoucher.getMinimumOrder().compareTo(minimumOrderBill) < 0) {
                    if (maxVoucher == null || currentVoucher.getMinimumOrder().compareTo(maxVoucher.getMinimumOrder()) > 0) {
                        maxVoucher = currentVoucher; // Cập nhật voucher lớn nhất
                    }
                }
            }

            if (maxVoucher != null) {
                // Sử dụng maxVoucher sau khi tìm thấy
                tBill.setVoucher(maxVoucher);
                System.out.println("Voucher lớn nhất phù hợp: " + maxVoucher);
                return billRepository.save(tBill);
            } else
                // Không tìm thấy voucher phù hợp
                return null;
        } else
        return null;
    }

    @Override
    public List<TBill> searchTBill(String searchTerm) {
        return billRepository.searchTBillBySearchTerm(searchTerm);
    }


    public Boolean checkQuantityVoucher(TVoucher tVoucher){
        if(tVoucher.getQuantity()>0){
            return true;
        }else
        return false;
    }

    public Boolean checkExistVoucher(List<TVoucher> tVoucherList){
        if(tVoucherList.size()>0){
            return true;
        } else return false;
    }
    public List<TVoucher> listVoucherBill(List<TVoucher> tVoucherList, BigDecimal minimumOrderBill) {
        List<TVoucher> eligibleVouchers = new ArrayList<>();

        for (TVoucher voucher : tVoucherList) {
            if (voucher.getMinimumOrder().compareTo(minimumOrderBill) < 0) {
                eligibleVouchers.add(voucher);
            }
        }
        return eligibleVouchers;
    }

    public TVoucher findMaxEligibleVoucher(List<TVoucher> tVoucherList, BigDecimal minimumOrderBill) {
        TVoucher maxVoucher = null;

        for (TVoucher voucher : tVoucherList) {
            if (voucher.getMinimumOrder().compareTo(minimumOrderBill) < 0) {
                if (maxVoucher == null || voucher.getMaximumCostReduction().compareTo(maxVoucher.getMaximumCostReduction()) > 0) {
                    maxVoucher = voucher;
                }
            }
        }

        return maxVoucher;
    }

}
