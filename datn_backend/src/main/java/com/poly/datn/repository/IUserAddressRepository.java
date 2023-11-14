package com.poly.datn.repository;

import com.poly.datn.model.TAddress;
import com.poly.datn.model.TUserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserAddressRepository extends JpaRepository<TUserAddress,Long> {

    @Query("SELECT u FROM TUserAddress u WHERE u.id = :id AND u.status = 1")
    TUserAddress findByIdUserAddress(Long id);

    @Query("SELECT a.address FROM TUserAddress a WHERE a.customer.id = :idUser AND a.status = 1")
    List<TAddress> findAllByUser(Long idUser);
}
