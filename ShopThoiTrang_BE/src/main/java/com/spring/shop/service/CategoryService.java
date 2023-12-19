package com.spring.shop.service;

import com.spring.shop.entity.Category;
import com.spring.shop.entity.ProductDetail;
import com.spring.shop.repository.CategoryRepository;
import com.spring.shop.repository.ProductDetailRepository;
import com.spring.shop.request.CategoryRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;
    public List<Category> getAll(){
        return repository.getAll();
    }
    public List<Category> getStopWorking(){
        return repository.getStopWorking();
    }
    public List<Category> getAllbyName(String name){
        return repository.searchByName('%'+name+'%');
    }
    public Category add(CategoryRequest request){
        Category category = new Category();
        category.setDescription(request.getDescription());
        category.setName(request.getName());
        category.setCreateDate(new Date());
        category.setStatus(0);
        return repository.save(category);
    }
    public Category update(Integer Id,CategoryRequest request){
        Category category = repository.getById(Id);
        category.setDescription(request.getDescription());
        category.setName(request.getName());
        category.setUpdateDate(new Date());
        return repository.save(category);
    }
    public Category deleteFake(Integer Id){
        Category category = repository.getById(Id);
        System.out.println(Id);

        category.setStatus(1);
        return repository.save(category);
    }

    public void delete(Integer Id){
        System.out.println("Ai đó đang ");
        Category category = repository.getById(Id);
        category.setStatus(1);
        repository.save(category);
    }
    public Category restore(Integer Id){
        Category category = repository.getById(Id);
        category.setStatus(0);
        return repository.save(category);
    }
    public Category getById(Integer Id){
        Category category = repository.getById(Id);
        return category;
    }
}
