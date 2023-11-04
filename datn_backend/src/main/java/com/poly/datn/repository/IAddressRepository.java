package com.poly.datn.repository;

import com.poly.datn.model.TAddress;
import com.poly.datn.model.TUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends JpaRepository<TAddress,Long> {

    @Query("SELECT a FROM TAddress a WHERE a.status = 1 " +
            "AND (a.province LIKE %:search% " +
            "OR a.district LIKE %:search% " +
            "OR a.ward LIKE %:search% " +
            "OR a.detailAddress LIKE %:search%)" +
            "ORDER BY a.province ASC")
    Page<TAddress> findAll(Pageable pageable, @Param("search") String search);

    @Query("SELECT a FROM TAddress a WHERE a.id = :id AND a.status = 1")
    TAddress findByIdAddress(Long id);
}
