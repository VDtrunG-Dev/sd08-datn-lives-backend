package com.poly.datn.repository;

import com.poly.datn.model.TRank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRankRepository extends JpaRepository<TRank, Long> {

    List<TRank> findByStatus(int status);

    Page<TRank> findRanksByStatus(int status, Pageable pageable);
}
