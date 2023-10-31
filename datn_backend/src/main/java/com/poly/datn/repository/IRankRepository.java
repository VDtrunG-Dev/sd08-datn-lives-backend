package com.poly.datn.repository;


import com.poly.datn.model.TPointTransactions;
import com.poly.datn.model.TRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRankRepository  extends JpaRepository<TRank, Long> {

    List<TRank> findByStatus(int status);

}
