package com.poly.datn.repository;

import com.poly.datn.model.TUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<TUser,Long> {

    @Query("SELECT u FROM TUser u WHERE u.status = 1")
    Page<TUser> findAll(Pageable pageable);

    @Query("SELECT u FROM TUser u WHERE u.id = :id AND u.status = 1")
    TUser findByIdUser(Long id);

    @Query("SELECT u FROM TUser u WHERE u.email = :email AND u.status = 1")
    TUser findByEmailUser(String email);

    @Query("SELECT u FROM TUser u WHERE u.status = :status")
    List<TUser> findByStatus(int status);

}