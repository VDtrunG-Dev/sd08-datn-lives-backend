package com.poly.datn.repository;

import com.poly.datn.model.TRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<TRole, Long> {
    List<TRole> findAll();
    Page<TRole> findByStatus(Integer status, Pageable pageable);

}