package com.spring.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.shop.entity.Size;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size,Integer> {
    @Query(value = "Select e from Size e Where e.Status = 0 order by e.CreateDate desc ")
    public List<Size> getAll();

    @Query(value = "Select e from Size e where e.Status = 1 order by e.CreateDate desc")
    public List<Size> getStopWorking();

    @Query(value = "Select e from Size e where e.Name like :name")
    public List<Size> searchByName(@Param("name") String name);

    @Query(value = "select e from Size e where e.Id = :id")
    public Size getById(@Param("id") Integer Id);
}
