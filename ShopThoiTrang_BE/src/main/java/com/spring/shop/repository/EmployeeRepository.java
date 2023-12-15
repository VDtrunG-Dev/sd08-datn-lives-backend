package com.spring.shop.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.shop.entity.Employee;

import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query(value = "Select e from Employee e where e.Status = 0  order by e.CreateDate desc")
    public List<Employee> getAll();
    @Query(value = "Select e from Employee e where e.Status = 1   order by e.CreateDate desc")
    public List<Employee> getInActive();

    @Query(value = "Select e from Employee e where e.Status = 0 and e.Fullname like :fullname")
    public List<Employee> searchByName(@Param("fullname") String fullname);
    @Query(value = "select e from Employee e where e.Id = :id")
    public Employee getById(@Param("id") Integer Id);
    @Query(value = "select e from Employee e where e.Username = :username")
    public Employee getByUsername(@Param("username") String username);

}
