package com.spring.shop.service;

import com.spring.shop.entity.Voucher;
import com.spring.shop.repository.ProductDetailRepository;
import com.spring.shop.repository.VoucherRepository;
import com.spring.shop.request.VoucherRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@Service
public class VoucherService {
    @Autowired
    VoucherRepository repository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    public List<Voucher> getAllActive(){
        return repository.getAllActive();
    }

    public List<Voucher> getAllPrepare(){
        return repository.getAllPrepare();
    }

    public List<Voucher> getAllStop(){
        return repository.getAllStop();
    }

    public List<Voucher> getAllDelete(){
        return repository.getAllDelete();
    }
    public List<Voucher> getAllbyName(String name){
        return repository.searchByName('%'+name+'%');
    }

    public Voucher getById(Integer Id){
        Voucher voucher = repository.getById(Id);
        return voucher;
    }
    //        Timestamp current = new Timestamp(System.currentTimeMillis());
////        LocalDateTime currentDate = LocalDateTime.now();
////        LocalDateTime expiredDate = currentDate.plusDays(3);
////        current.
    public Voucher add(VoucherRequest request){
        Voucher voucher = new Voucher();
        voucher.setCode(request.getCode());
        voucher.setName(request.getName());
        voucher.setTypeVoucher(request.getTypeVoucher());
        voucher.setIsVoucher(false);
        voucher.setDiscount(request.getDiscount());
        voucher.setCash(request.getCash());
        voucher.setStartDate(request.getStartDate());
        voucher.setEndDate(request.getEndDate());
        voucher.setStatus(0);
        voucher.setMinimum(request.getMinimum());
        voucher.setCreateDate(new java.sql.Timestamp(new java.util.Date().getTime()));

        return repository.save(voucher);
    }

    //        Timestamp current = new Timestamp(System.currentTimeMillis());
//        LocalDateTime currentDate = LocalDateTime.now();
//        LocalDateTime expiredDate = currentDate.plusDays(3);
//        current.
    public Voucher update(Integer id, VoucherRequest request){
        Voucher voucher = repository.getById(id);
        voucher.setCode(request.getCode());
        voucher.setName(request.getName());
        voucher.setTypeVoucher(request.getTypeVoucher());
        voucher.setIsVoucher(request.getIsVoucher());
        voucher.setDiscount(request.getDiscount());
        voucher.setCash(request.getCash());
        voucher.setStartDate(request.getStartDate());
        voucher.setEndDate(request.getEndDate());
        voucher.setStatus(0);
        voucher.setMinimum(request.getMinimum());
        return repository.save(voucher);
    }

    public Voucher delete(Integer Id){
        Voucher voucher = repository.getById(Id);
        voucher.setStatus(1);
        return repository.save(voucher);
    }

    public Voucher getVoucherTop(Integer totalPrice) {
        List<Voucher> listVoucher = productDetailRepository.getAllVoucherbyTongTien(totalPrice);
        HashMap<Integer, BigDecimal> hashMap = new HashMap<>();

        for (Voucher v : listVoucher) {
            if (!v.getTypeVoucher()) {
                // Giảm tiền
                if (v.getCash() != null) {
                    hashMap.put(v.getId(), v.getCash());
                }
            } else {
                // Giảm phần trăm
                if (v.getDiscount() != null) {
                    double tienGiam1 = totalPrice * (v.getDiscount() / 100.0);
                    BigDecimal tienGiam = BigDecimal.valueOf(tienGiam1);
                    hashMap.put(v.getId(), tienGiam);
                }
            }
        }

        List<Map.Entry<Integer, BigDecimal>> list = new LinkedList<>(hashMap.entrySet());

        if (list.isEmpty()) {
            // Trả về giá trị mặc định hoặc xử lý tình huống không tìm thấy voucher
            return null; // hoặc throw new YourCustomException("No voucher found");
        }

        Collections.sort(list, Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()));

        Map<Integer, BigDecimal> sortedHashMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, BigDecimal> entry : list) {
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }

        Integer topVoucherId = sortedHashMap.entrySet().stream().findFirst().map(Map.Entry::getKey).orElse(null);

        if (topVoucherId != null) {
            return repository.getById(topVoucherId);
        } else {
            return null;
        }
    }




}
