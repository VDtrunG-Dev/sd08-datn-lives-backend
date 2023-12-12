package com.spring.shop.service;

import com.spring.shop.entity.Voucher;
import com.spring.shop.repository.ProductDetailRepository;
import com.spring.shop.repository.VoucherRepository;
import com.spring.shop.request.VoucherRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class VoucherService {
    @Autowired
    VoucherRepository repository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    public List<Voucher> getAll(){
        return repository.getAll();
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
        voucher.setIsVoucher(request.getIsVoucher());
        voucher.setDiscount(request.getDiscount());
        voucher.setCash(request.getCash());
        voucher.setStartDate(request.getStartDate());
        voucher.setEndDate(request.getEndDate());
        voucher.setStatus(0);
        voucher.setMinimum(request.getMinimum());

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

        BigDecimal tienGiam = null;
        for (Voucher v : listVoucher) {
            tienGiam = new BigDecimal(0.0);
            if (v.getTypeVoucher() == false) {
                // Giảm tiền
                hashMap.put(v.getId(), v.getCash());
            } else {
                // Giảm phần trăm
                double tienGiam1 = totalPrice * (v.getDiscount() / 100.0);
                tienGiam = BigDecimal.valueOf(tienGiam1);
                hashMap.put(v.getId(), tienGiam);
            }
        }
        List<Map.Entry<Integer, BigDecimal>> list = new LinkedList<>(hashMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, BigDecimal>>() {
            @Override
            public int compare(Map.Entry<Integer, BigDecimal> o1, Map.Entry<Integer, BigDecimal> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }

        });
        Map<Integer, BigDecimal> sortedHashMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, BigDecimal> entry : list) {
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        Optional<Map.Entry<Integer, BigDecimal>> firstEntry = sortedHashMap.entrySet().stream().findFirst();
        Voucher voucher = repository.getById(firstEntry.get().getKey());
        return voucher;
    }


}
