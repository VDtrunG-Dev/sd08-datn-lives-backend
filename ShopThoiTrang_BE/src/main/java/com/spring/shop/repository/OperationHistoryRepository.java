package com.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.shop.entity.OperationHistory;

import java.util.List;

@Repository
public interface OperationHistoryRepository extends JpaRepository<OperationHistory,Integer> {

    @Query(value = "select e from OperationHistory e order by e.CreateDate desc")
    public List<OperationHistory> getAll();
}
