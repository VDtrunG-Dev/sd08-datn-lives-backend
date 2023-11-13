package com.poly.datn.service.impl;

import com.poly.datn.dto.BillDetailRequest;
import com.poly.datn.dto.BillRequest;
import com.poly.datn.model.TBillDetail;
import com.poly.datn.model.TVoucher;
import com.poly.datn.repository.BillDetailRepository;
import com.poly.datn.repository.IVoucherRepository;
import com.poly.datn.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BillDetailServiceImpl implements BillDetailService {
    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private IVoucherRepository voucherRepository;
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
        List<TBillDetail> foundPaymentypes = billDetailRepository.findByBillId(request.getBillId().getId());
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

    @Override
    public Page<TBillDetail> PageGetAllBillDetailsDaThanhToan(Pageable pageable) {
        return billDetailRepository.PageGetAllBillDetailsDaThanhToan(pageable);
    }

    @Override
    public Page<TBillDetail> PageGetAllBillDetailsDaHuy(Pageable pageable) {
        return billDetailRepository.PageGetAllBillDetailsDaHuy(pageable);
    }

    @Override
    public Page<TBillDetail> PageGetAllBillDetailsVanChuyen(Pageable pageable) {
        return billDetailRepository.PageGetAllBillDetailsVanChuyen(pageable);
    }

    @Override
    public Page<TBillDetail> PageGetAllBillDetailsDangHoan(Pageable pageable) {
        return billDetailRepository.PageGetAllBillDetailsDangHoan(pageable);
    }

    @Override
    public Page<TBillDetail> PageGetAllBillDetailsDaHoanKiemTra(Pageable pageable) {
        return billDetailRepository.PageGetAllBillDetailsDaHoanKiemTra(pageable);
    }

    @Override
    public Page<TBillDetail> PageGetAllBillDetailsDaTraHoanTien(Pageable pageable) {
        return billDetailRepository.PageGetAllBillDetailsDaTraHoanTien(pageable);
    }

    @Override
    public Page<TBillDetail> PageGetAllBillDetailsDoiTra(Pageable pageable) {
        return billDetailRepository.PageGetAllBillDetailsDoiTra(pageable);
    }
    public Boolean checkProductQuantity(BillDetailRequest request){
        if(request.getProductVariation().getStatus()==2){
            return true;
        } else if(request.getProductVariation().getQuantity()> request.getQuantity()){
            return true;
        }
        return false;
    }

    public Boolean checkStatusProduct(){
        return false;
    }



}
