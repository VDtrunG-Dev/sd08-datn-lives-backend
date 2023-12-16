package com.spring.shop.service;

import com.spring.shop.entity.*;
import com.spring.shop.repository.AddressRepository;
import com.spring.shop.repository.BillDetailRepository;
import com.spring.shop.repository.BillHistoryRepository;
import com.spring.shop.repository.BillRepository;
import com.spring.shop.request.*;
import com.spring.shop.response.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.JSqlParserUtils;
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

    @Autowired
    BillHistoryRepository repositoryBillHistory;

    @Autowired
    BillDetailRepository repositoryBillDetail;

    @Autowired
    AddressRepository addressRepository;
    public String genCode(){
        // Tạo đối tượng Random
        long timestamp = Instant.now().getEpochSecond();
        String code = "HD" + timestamp;
    return code;
    }

    public Bill add(BillRequest request){
        Address address = addressRepository.getAddressById(request.getIdAddress());
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
        if(request.getIdAddress() != 0){
            bill.setFullname(address.getFullname());
            bill.setPhone(address.getPhone());
            bill.setAddressDetail(address.getAddress());
            bill.setCityName(address.getCityName());
            bill.setDistrictName(address.getDistrictName());
            bill.setWardName(address.getWardName());
            bill.setCityId(address.getCityId());
            bill.setDistrictId(address.getDistrictId());
            bill.setWardId(address.getWardId());
        }
        if(request.getIdCustomer() != -1){
            bill.setCustomer(Customer.builder().Id(request.getIdCustomer()).build());
        }
        bill.setStatus(request.getStatus());
        bill.setTypeStatus(request.getTypeStatus());
        return repository.save(bill);

    }
    public Bill update(String code, BillTaiQuayUpdateRequest request){
        Bill bill = repository.getByCode(code);
        Address address = addressRepository.getAddressById(request.getIdAddress());
        bill.setNote(request.getNote());
        bill.setShipPrice(request.getShipPrice());
        bill.setTotalPrice(request.getTotalPrice());
        bill.setTotalPriceLast(request.getTotalPriceLast());
        bill.setPayStatus(request.getPayStatus());
        bill.setPayType(request.getPayType());
        bill.setIdCoupon(request.getIdCoupon());

       if(request.getIdAddress() != null && request.getIdAddress() != 0){
           bill.setFullname(address.getFullname());
           bill.setPhone(address.getPhone());
           bill.setAddressDetail(address.getAddress());
           bill.setCityName(address.getCityName());
           bill.setDistrictName(address.getDistrictName());
           bill.setWardName(address.getWardName());
           bill.setCityId(address.getCityId());
           bill.setDistrictId(address.getDistrictId());
           bill.setWardId(address.getWardId());
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
        bill.setStatus(request.getStatus());
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
    public Bill updateDiaChi(String Code,AddressKhachLe addressKhachLe ){
        Bill bill = repository.getByCode(Code);
        bill.setFullname(addressKhachLe.getFullname());
        bill.setPhone(addressKhachLe.getPhone());
        bill.setAddressDetail(addressKhachLe.getAddress());
        bill.setCityName(addressKhachLe.getCityName());
        bill.setDistrictName(addressKhachLe.getDistrictName());
        bill.setWardName(addressKhachLe.getWardName());
        bill.setCityId(addressKhachLe.getCityId());
        bill.setDistrictId(addressKhachLe.getDistrictId());
        bill.setWardId(addressKhachLe.getWardId());
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
        for(BillHistory billHistory : repositoryBillHistory.getAllByBillCode(code) ){
            repositoryBillHistory.delete(billHistory);
        }
        for(BillDetail billDetail : repositoryBillDetail.getAllByBill(code) ){
            repositoryBillDetail.delete(billDetail);
        }
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
    public TKTuan getTKTuan(){
        return repository.getThongKeTuan();
    }
    public TKThang getTKThang(){
        return repository.getThongKeThang();
    }
    public TKNam getTKNam(){
        return repository.getThongKeNam();
    }
    public TKSLThang getTKSLThang(){
        return repository.getThongKeSoLuongThang();
    }
    public List<TKKhoangNgay> getTKSoLuongHD(String tungay, String denngay){
        return repository.getTKKhoangNgay(tungay,denngay);
    }
    public List<TKSoLuongSanPham> getTKSoLuongSanPham(String tungay, String denngay){
        return repository.getTKSoLuongSanPham(tungay,denngay);
    }


}
