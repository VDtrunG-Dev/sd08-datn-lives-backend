package com.poly.datn.repository;

import com.poly.datn.model.TPoints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPointRepository extends JpaRepository<TPoints, Long> {

    List<TPoints> findByStatus(int status);


    Page<TPoints> findAllByStatus(int status, Pageable pageable);

    List<TPoints> findAll(Specification<TPoints> tPointsSpecification);
}
