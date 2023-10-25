package com.poly.datn.repository;

import com.poly.datn.model.TUserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserAddressRepository extends JpaRepository<TUserAddress,Long> {

    @Query("SELECT u FROM TUserAddress u WHERE u.id = :id")
    TUserAddress findByIdUserAddress(Long id);
}
