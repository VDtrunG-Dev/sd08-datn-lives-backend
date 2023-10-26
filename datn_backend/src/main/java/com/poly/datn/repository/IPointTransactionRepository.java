package com.poly.datn.repository;

import com.poly.datn.model.TPointTransactions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPointTransactionRepository extends JpaRepository<TPointTransactions, Long> {

    Page<TPointTransactions> findAll(Pageable pageable);
}
