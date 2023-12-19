package com.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.shop.entity.Voucher;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    @Query("SELECT e FROM Voucher e WHERE e.Status = 0 AND e.StartDate <= CURRENT_DATE AND e.EndDate >= CURRENT_DATE")
    public List<Voucher> getAllActive();

    @Query("SELECT e FROM Voucher e WHERE e.Status = 0 AND e.StartDate > CURRENT_DATE  AND e.EndDate > CURRENT_DATE ORDER BY e.StartDate DESC")
    public List<Voucher> getAllPrepare();

    @Query("SELECT e FROM Voucher e WHERE e.Status = 0 AND e.StartDate < CURRENT_DATE AND e.EndDate < CURRENT_DATE")
    public List<Voucher> getAllStop();

    @Query("SELECT e FROM Voucher e WHERE e.Status = 1")
    public List<Voucher> getAllDelete();
    @Query(value = "Select e from Voucher e where e.Status = 0 and e.Name like :name")
    public List<Voucher> searchByName(@Param("name") String name);
    @Query(value = "select e from Voucher e where e.Id = :id")
    public Voucher getById(@Param("id") Integer Id);
}
