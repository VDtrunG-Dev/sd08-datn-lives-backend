package com.spring.shop.service;

import com.spring.shop.entity.Brand;
import com.spring.shop.entity.Category;
import com.spring.shop.repository.BrandRepository;
import com.spring.shop.repository.CategoryRepository;
import com.spring.shop.request.BrandRequest;
import com.spring.shop.request.CategoryRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BrandService {
    @Autowired
    BrandRepository repository;
    public List<Brand> getAll(){
        return repository.getAll();
    }

    public List<Brand> getAllbyName(String name){
        return repository.searchByName('%'+name+'%');
    }
    public Brand add(BrandRequest request){
        Brand brand = new Brand();
        brand.setDescription(request.getDescription());
        brand.setName(request.getName());
        brand.setCreateDate(new Date());
        brand.setStatus(0);
        return repository.save(brand);
    }
    public Brand update(Integer Id,BrandRequest request){
        Brand brand = repository.getById(Id);
        brand.setDescription(request.getDescription());
        brand.setName(request.getName());
        brand.setUpdateDate(new Date());
        return repository.save(brand);
    }
    public Brand delete(Integer Id){
        Brand brand = repository.getById(Id);
        brand.setStatus(1);
        return repository.save(brand);
    }
    public Brand getById(Integer Id){
        Brand brand = repository.getById(Id);
        return brand;
    }
    public Brand restore(Integer Id){
        Brand Brand = repository.getById(Id);
        Brand.setStatus(0);
        return repository.save(Brand);
    }

    public Brand deleteFake(Integer Id){
        Brand Brand = repository.getById(Id);
        Brand.setStatus(1);
        return repository.save(Brand);
    }

    public List<Brand> getInActive() {
        return repository.getInActive();
    }
}
