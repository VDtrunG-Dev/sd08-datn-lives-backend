package com.poly.datn.repository;


import com.poly.datn.model.TRank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRankRepository extends JpaRepository<TRank, Long> {

    List<TRank> findByStatus(int status);

    Page<TRank> findRanksByStatus(int status, Pageable pageable);

    @Query("SELECT r FROM TRank r WHERE r.id = :id AND r.status = 1")
    TRank findByIdRank(Long id);

}
