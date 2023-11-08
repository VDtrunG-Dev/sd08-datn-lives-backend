package com.poly.datn.repository;

import com.poly.datn.model.TBillDetail;
import com.poly.datn.model.TBillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<TBillDetail,Long> {
    @Query(value = "select id,bill_id,product_variation_id,quantity,price,discount,tax,unit_price,subtotal_price,status from t_bill_detail",nativeQuery = true)
    List<TBillDetail> getAllActive();

    List<TBillDetail> findByBillId(Long billId);

    @Query(value = "select id,bill_id,product_variation_id,quantity,price,discount,tax,unit_price,subtotal_price,status from t_bill_detail",nativeQuery = true)
    Page<TBillDetail> PageGetAllBillDetails(Pageable pageable);

    @Query(value = "select id,bill_id,product_variation_id,quantity,price,discount,tax,unit_price,subtotal_price,status from t_bill_detail where status =1",nativeQuery = true)
    Page<TBillDetail> PageGetAllBillDetailsDaThanhToan(Pageable pageable);

    @Query(value = "select id,bill_id,product_variation_id,quantity,price,discount,tax,unit_price,subtotal_price,status from t_bill_detail where status =2",nativeQuery = true)
    Page<TBillDetail> PageGetAllBillDetailsDaHuy(Pageable pageable);

    @Query(value = "select id,bill_id,product_variation_id,quantity,price,discount,tax,unit_price,subtotal_price,status from t_bill_detail where status =3",nativeQuery = true)
    Page<TBillDetail> PageGetAllBillDetailsVanChuyen(Pageable pageable);

    @Query(value = "select id,bill_id,product_variation_id,quantity,price,discount,tax,unit_price,subtotal_price,status from t_bill_detail where status =4",nativeQuery = true)
    Page<TBillDetail> PageGetAllBillDetailsDangHoan(Pageable pageable);

    @Query(value = "select id,bill_id,product_variation_id,quantity,price,discount,tax,unit_price,subtotal_price,status from t_bill_detail where status =5",nativeQuery = true)
    Page<TBillDetail> PageGetAllBillDetailsDaHoanKiemTra(Pageable pageable);

    @Query(value = "select id,bill_id,product_variation_id,quantity,price,discount,tax,unit_price,subtotal_price,status from t_bill_detail where status =6",nativeQuery = true)
    Page<TBillDetail> PageGetAllBillDetailsDaTraHoanTien(Pageable pageable);

    @Query(value = "select id,bill_id,product_variation_id,quantity,price,discount,tax,unit_price,subtotal_price,status from t_bill_detail where status =7",nativeQuery = true)
    Page<TBillDetail> PageGetAllBillDetailsDoiTra(Pageable pageable);

}
