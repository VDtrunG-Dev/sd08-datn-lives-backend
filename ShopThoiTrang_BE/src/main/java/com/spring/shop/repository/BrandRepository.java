package com.spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.shop.entity.Brand;
import com.spring.shop.entity.Category;
import com.spring.shop.entity.Size;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    @Query(value = "Select e from Brand e where e.Status = 0 order by e.CreateDate desc ")
    public List<Brand> getAll();
    @Query(value = "Select e from Brand e where e.Status = 0 and e.Name like :name")
    public List<Brand> searchByName(@Param("name") String name);
    @Query(value = "select e from Brand e where e.Id = :id")
    public Brand getById(@Param("id") Integer Id);
}
