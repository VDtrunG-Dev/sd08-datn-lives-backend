package com.poly.datn.repository;

import com.poly.datn.model.TPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPointRepository extends JpaRepository<TPoints, Long> {
}
