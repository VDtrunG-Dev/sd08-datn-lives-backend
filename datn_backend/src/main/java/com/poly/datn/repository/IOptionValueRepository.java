package com.poly.datn.repository;

import com.poly.datn.model.TOptionValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOptionValueRepository extends JpaRepository<TOptionValue,Long> {

    @Query("SELECT o FROM TOptionValue o WHERE o.status = 1")
    Page<TOptionValue> findAll(Pageable pageable);

    @Query("SELECT o FROM TOptionValue o WHERE o.id = :id")
    TOptionValue findByIdOptionValue(Long id);

    @Query("SELECT o FROM TOptionValue o WHERE o.valueName = :name")
    TOptionValue findByName(String name);

}
