package com.poly.datn.repository;

import com.poly.datn.model.TOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOptionRepository extends JpaRepository<TOption,Long> {

    @Query("SELECT o FROM TOption o WHERE o.status = 1")
    Page<TOption> findALl(Pageable pageable);

    @Query("SELECT o FROM TOption o WHERE o.name = :name")
    TOption findTOptionsByName(String name);

    @Query("SELECT o FROM TOption o WHERE o.id = :id")
    TOption findTOptionsById(Long id);

    @Query("SELECT o.name FROM TOption o WHERE o.status = 1")
    List<String> getOptionName();

    List<TOption> findByNameContaining(String keyword);
}
