package com.poly.datn.repository;

import com.poly.datn.model.TAddress;
import com.poly.datn.model.TUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAddressRepository extends JpaRepository<TAddress,Long> {

    @Query("SELECT a FROM TAddress a WHERE a.status = 1")
    List<TAddress> findAll();

    @Query("SELECT a FROM TAddress a WHERE a.status = 0")
    List<TAddress> findByStatus0();

    @Query("SELECT a FROM TAddress a WHERE a.status = 1")
    Page<TAddress> findAll(Pageable pageable);

    @Query("SELECT a FROM TAddress a WHERE a.id = :id AND a.status = 1")
    TAddress findByIdAddress(Long id);


}
