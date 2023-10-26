package com.poly.datn.repository;

import com.poly.datn.model.TProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductOptionRepository extends JpaRepository<TProductOption,Long> {

    @Query("SELECT p FROM TProductOption p WHERE p.id = :id")
    TProductOption findByIdProductOption(Long id);
}
