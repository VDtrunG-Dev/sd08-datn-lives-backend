package com.poly.datn.repository;


import com.poly.datn.model.TRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRankRepository  extends JpaRepository<TRank, Long> {
}
