package com.poly.datn.repository;

import com.poly.datn.model.TAddress;
import com.poly.datn.model.TUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends JpaRepository<TAddress,Long> {

    @Query("SELECT a FROM TAddress a WHERE a.id = :id")
    TAddress findByIdAddress(Long id);
}
