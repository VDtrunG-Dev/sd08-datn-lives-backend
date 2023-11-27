package com.spring.shop.repository;

import com.spring.shop.entity.Bill;
import com.spring.shop.response.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {
    @Query(value = "select  e from Bill e where e.Code = :code")
    public Bill getByCode(@Param("code") String code);

    @Query(value = "Select b.Id , b.Code,b.PurchaseDate, b.EstimatedDate, b.PaymentDate, b.DelyveryDate, b.TotalPrice, b.ShipPrice ,b.TotalPriceLast, b.Note, b.PayType, b.PayStatus,b.IdCoupon, b.IdAddress , b.IdCustomer, b.IdVoucher , b.IdEmployee ,b.Status from Bill b \n" +
            "join Customer c on c.Id = b.IdCustomer " +
            "where (b.Status = :status or :status is null) and c.Id = :idCustomer", nativeQuery = true)

    public List<BillResponse> getBillByCustomer(@Param("status") Integer status , @Param("idCustomer") Integer idCustomer);
    @Query(value = "Select b.Id , b.Code,b.PurchaseDate, b.EstimatedDate, b.PaymentDate, b.DelyveryDate, b.TotalPrice, b.ShipPrice ,b.TotalPriceLast, b.Note, b.PayType, b.PayStatus,b.IdCoupon, b.IdAddress , b.IdCustomer, b.IdVoucher , b.IdEmployee ,b.Status, b.TypeStatus from Bill b \n" +
            "where b.Code = :code order by b.PurchaseDate desc", nativeQuery = true)
    public BillResponse getBillBycode(@Param("code") String code);

    @Query(value = "Select b.Id , b.Code,b.PurchaseDate, b.EstimatedDate, b.PaymentDate, b.DelyveryDate, b.TotalPrice, b.ShipPrice ,b.TotalPriceLast, b.Note, b.PayType, b.PayStatus,b.IdCoupon, b.IdAddress , b.IdCustomer, b.IdVoucher , b.IdEmployee ,b.Status , c.Username from Bill b \n" +
            "join Customer c on c.Id = b.IdCustomer order by b.PurchaseDate desc", nativeQuery = true)
    public List<BillAllResponse> getAllBill();

    @Query(value = "Select b.Id , b.Code,b.PurchaseDate, b.EstimatedDate, b.PaymentDate, b.DelyveryDate, b.TotalPrice, b.ShipPrice ,b.TotalPriceLast, b.Note, b.PayType, b.PayStatus,b.IdCoupon, b.IdAddress , b.IdCustomer, b.IdVoucher , b.IdEmployee ,b.Status, b.TypeStatus from Bill b \n" +
            "where b.Status = :status order by b.PurchaseDate desc", nativeQuery = true)
    public List<BillResponse> getBillByStatus(@Param("status") Integer status);

    @Query(value = "Select b.Id , b.Code,b.PurchaseDate, b.EstimatedDate, b.PaymentDate, b.DelyveryDate, b.TotalPrice, b.ShipPrice ,b.TotalPriceLast, b.Note, b.PayType, b.PayStatus,b.IdCoupon, b.IdAddress , b.IdCustomer, b.IdVoucher , b.IdEmployee ,b.Status, b.TypeStatus from Bill b \n" +
            "where (b.Status = :status or :status is null) and (b.PayStatus = :payStatus or :payStatus is null) and (b.PayType = :payType or :payType is null) and (b.TypeStatus = :typeStatus or :typeStatus is null) and (b.PurchaseDate >= :tungay or :tungay is null) and (b.PurchaseDate <= :denngay or :denngay is null)  order by b.PurchaseDate desc", nativeQuery = true)
    public List<BillResponse> getBillFilter(@Param("status") Integer status,@Param("payStatus") Integer payStatus,@Param("payType") Integer payType,@Param("typeStatus") Integer typeStatus,@Param("tungay") String tungay,@Param("denngay") String denngay );
    @Query(value = "Select b.Id , b.Code,b.PurchaseDate, b.EstimatedDate, b.PaymentDate, b.DelyveryDate, b.TotalPrice, b.ShipPrice ,b.TotalPriceLast, b.Note, b.PayType, b.PayStatus,b.IdCoupon, b.IdAddress , b.IdCustomer, b.IdVoucher , b.IdEmployee ,b.Status, b.TypeStatus from Bill b \n" +
            "where b.Status = 0 or b.Status = 1 or b.Status = 2 or b.Status = 3 or b.Status = 4 or b.Status = 5  order by b.PurchaseDate desc", nativeQuery = true)
    public List<BillResponse> getAll();


    @Query(value = "Select COUNT(b.Id) as 'SoLuong', SUM(b.TotalPrice) as 'DoanhThu' from Bill b\n" +
            "where b.Status = 3 and CONVERT(DATE, b.PurchaseDate) = CONVERT(DATE, GETDATE())", nativeQuery = true)
    public TKNgay getThongKeNgay();

    @Query(value = "SELECT COUNT(b.Id) as 'SoLuong', SUM(b.TotalPrice) as 'DoanhThu'\n" +
            "FROM Bill b\n" +
            "WHERE b.Status = 3 AND MONTH(b.PurchaseDate) = MONTH(GETDATE()) AND YEAR(b.PurchaseDate) = YEAR(GETDATE())", nativeQuery = true)
    public TKThang getThongKeThang();

    @Query(value = "Select SUM(bi.Quantity) as 'SoLuong' from BillDetail bi\n" +
            "join Bill b on b.Id = bi.IdOrder\n" +
            "WHERE b.Status = 3 AND MONTH(b.PurchaseDate) = MONTH(GETDATE()) AND YEAR(b.PurchaseDate) = YEAR(GETDATE())",nativeQuery = true)
    public TKSLThang getThongKeSoLuongThang();

    @Query(value = "WITH DateTable AS (\n" +
            "    SELECT \n" +
            "        DATEADD(DAY, number, :tungay) AS DateInInterval\n" +
            "    FROM master.dbo.spt_values\n" +
            "    WHERE type = 'P'\n" +
            "        AND DATEADD(DAY, number, :tungay) <= :denngay\n" +
            ")\n" +
            "\n" +
            "SELECT \n" +
            "CAST(DateTable.DateInInterval AS DATE) AS PurchaseDay,\n" +
            "    COUNT(Bill.PurchaseDate) AS NumberOfBills\n" +
            "FROM DateTable\n" +
            "LEFT JOIN Bill ON CONVERT(DATE, Bill.PurchaseDate) = DateTable.DateInInterval\n" +
            "GROUP BY CAST(DateTable.DateInInterval AS DATE)\n" +
            "ORDER BY PurchaseDay",nativeQuery = true)
    public List<TKSoLuongHD> getTKSoLuongHD(@Param("tungay")String tungay, @Param("denngay") String denngay);

    @Query(value = "WITH DateTable AS (\n" +
            "    SELECT \n" +
            "        DATEADD(DAY, number, :tungay) AS DateInInterval\n" +
            "    FROM master.dbo.spt_values\n" +
            "    WHERE type = 'P'\n" +
            "        AND DATEADD(DAY, number, :tungay) <= :denngay\n" +
            ")\n" +
            "\n" +
            "SELECT \n" +
            "    CAST(DateTable.DateInInterval AS DATE) AS PurchaseDay,\n" +
            "    COALESCE(SUM(bi.Quantity), 0) AS SoLuong,\n" +
            "    COALESCE(SUM(bi.Quantity * bi.UnitPrice), 0) AS DoanhThu\n" +
            "FROM DateTable\n" +
            "LEFT JOIN Bill b ON CONVERT(DATE, b.PurchaseDate) = DateTable.DateInInterval AND b.Status = 3\n" +
            "LEFT JOIN BillDetail bi ON bi.IdOrder = b.Id\n" +
            "GROUP BY CAST(DateTable.DateInInterval AS DATE)\n" +
            "ORDER BY PurchaseDay",nativeQuery = true)
    public List<TKSoLuongSanPham> getTKSoLuongSanPham(@Param("tungay")String tungay, @Param("denngay") String denngay);

}
