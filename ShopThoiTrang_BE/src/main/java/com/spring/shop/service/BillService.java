package com.spring.shop.service;

import com.spring.shop.entity.*;
import com.spring.shop.repository.BillRepository;
import com.spring.shop.request.*;
import com.spring.shop.response.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class BillService {
    @Autowired
    BillRepository repository;

    public String genCode(){
        // Tạo đối tượng Random
        long timestamp = Instant.now().getEpochSecond();
        String code = "HD" + timestamp;
    return code;
    }

    public Bill add(BillRequest request){
        Bill bill = new Bill();
        bill.setCode(genCode());
        bill.setPurchaseDate(new Date());
        bill.setNote(request.getNote());
        bill.setShipPrice(request.getShipPrice());
        bill.setTotalPrice(request.getTotalPrice());
        bill.setTotalPriceLast(request.getTotalPriceLast());
        bill.setPayStatus(request.getPayStatus());
        bill.setPayType(request.getPayType());
        bill.setIdCoupon(request.getIdCoupon());
        bill.setAddress(Address.builder().Id(request.getIdAddress()).build());
        if(request.getIdCustomer() != -1){
            bill.setCustomer(Customer.builder().Id(request.getIdCustomer()).build());
        }
        bill.setStatus(request.getStatus());
        bill.setTypeStatus(request.getTypeStatus());
        return repository.save(bill);

    }
    public Bill update(String code, BillTaiQuayUpdateRequest request){
        Bill bill = repository.getByCode(code);
        bill.setNote(request.getNote());
        bill.setShipPrice(request.getShipPrice());
        bill.setTotalPrice(request.getTotalPrice());
        bill.setTotalPriceLast(request.getTotalPriceLast());
        bill.setPayStatus(request.getPayStatus());
        bill.setPayType(request.getPayType());
        bill.setIdCoupon(request.getIdCoupon());
       if(request.getIdAddress() != 0){
           bill.setAddress(Address.builder().Id(request.getIdAddress()).build());
       }
        bill.setStatus(request.getStatus());
        bill.setPaymentDate(request.getPaymentDate());
        bill.setDelyveryDate(request.getDelyveryDate());
        if(request.getIdCustomer() != 0){
            bill.setCustomer(Customer.builder().Id(request.getIdCustomer()).build());
        }
        if(request.getIdVoucher() != 0 && request.getIdVoucher() != null){
            bill.setVoucher(Voucher.builder().Id(request.getIdVoucher()).build());
        }
        bill.setTypeStatus(request.getTypeStatus());
        return repository.save(bill);
    }
    public Bill updateStatus(String code, UpdateThanhToanTaiQuay request){
        Bill bill = repository.getByCode(code);

        bill.setStatus(request.getStatus());
        bill.setDelyveryDate(request.getDeliveryDate());
        bill.setPayStatus(request.getPayStatus());
        bill.setPaymentDate(request.getPaymentDate());
        return repository.save(bill);

    }
    public List<BillResponse> getBillFilter(Integer status,Integer payStatus,Integer payType,Integer typeStatus,String tungay,String denngay ){
        return repository.getBillFilter(status,payStatus,payType,typeStatus,tungay,denngay);
    }
    public Bill updateStatus1(String code, UpdateThanhToanTaiQuay request){
        Bill bill = repository.getByCode(code);
        bill.setDelyveryDate(new Date());
        bill.setPayStatus(request.getPayStatus());
        bill.setPaymentDate(new Date());
        return repository.save(bill);

    }
    public Bill updateStatusPay(String code){
    Bill bill = repository.getByCode(code);
    bill.setPayStatus(1);
    bill.setStatus(0);
    bill.setPaymentDate(new Date());
    return repository.save(bill);
    }
    public Bill updateStatus(UpdateBillStatus updateBillStatus){
        Bill bill = repository.getByCode(updateBillStatus.getCode());
        bill.setStatus(updateBillStatus.getStatus());
        return repository.save(bill);
    }
    public Bill updateDiaChi(String Code , Integer IdDiachi){
        Bill bill = repository.getByCode(Code);
        bill.setAddress(Address.builder().Id(IdDiachi).build());
        return repository.save(bill);
    }
    public Bill updateTongTien(String Code , BigDecimal money){
        Bill bill = repository.getByCode(Code);
        bill.setTotalPrice(money);
        return repository.save(bill);
    }
    public void huyBill(String code){
        Bill bill = repository.getByCode(code);
        bill.setStatus(4);
        repository.save(bill);
    }
    public void deleteBill(String code){
        Bill bill = repository.getByCode(code);
        repository.delete(bill);
    }
    public List<BillResponse> getBillByCustomer(Integer status , Integer idCustomer){
        return repository.getBillByCustomer(status,idCustomer);
    }
    public List<BillAllResponse> getAllBill(){
        return repository.getAllBill();
    }
    public BillResponse getByCode(String code){
        return repository.getBillBycode(code);
    }
    public List<BillResponse> getAllByStatus(Integer status){
        return repository.getBillByStatus(status);
    }
    public Bill addBillTaiQuay(BillTaiQuayRequest request){
        Bill bill = new Bill();
        bill.setCode(genCode());
        bill.setPurchaseDate(new Date());
        bill.setTypeStatus(request.getTypeStatus());
        bill.setStatus(request.getStatus());
        bill.setEmployee(Employee.builder().Id(request.getIdEmployee()).build());
        return repository.save(bill);
    }
    public List<BillResponse> getAll(){
        return repository.getAll();
    }

    public TKNgay getTKNgay(){
        return repository.getThongKeNgay();
    }
    public TKThang getTKThang(){
        return repository.getThongKeThang();
    }
    public TKSLThang getTKSLThang(){
        return repository.getThongKeSoLuongThang();
    }
    public List<TKSoLuongHD> getTKSoLuongHD(String tungay, String denngay){
        return repository.getTKSoLuongHD(tungay,denngay);
    }
    public List<TKSoLuongSanPham> getTKSoLuongSanPham(String tungay, String denngay){
        return repository.getTKSoLuongSanPham(tungay,denngay);
    }
}
