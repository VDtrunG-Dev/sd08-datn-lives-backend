package com.poly.datn.service;


import com.poly.datn.model.TVoucher;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

public interface IVoucherService {

    List<TVoucher> getAllVoucher();

    Page<TVoucher> getAllPaged(int page, int size);

    Optional<TVoucher> getVoucherById(Long id);

    TVoucher addVoucher(TVoucher tVoucher);

    TVoucher updateVoucher(Long id, TVoucher updatedVoucher);

    boolean deleteVoucher(Long id);

    List<TVoucher> getAllVouchersByStatus(int status);


    Page<TVoucher> getAllByStatusPaged(int status, int page, int size);

    List<TVoucher> searchAll(String voucherName, String voucherCode, Integer status);

}
