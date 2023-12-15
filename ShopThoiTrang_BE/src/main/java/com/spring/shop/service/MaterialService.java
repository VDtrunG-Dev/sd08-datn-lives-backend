package com.spring.shop.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.entity.Material;
import com.spring.shop.repository.MaterialRepository;
import com.spring.shop.request.MaterialRequest;

@Service
public class MaterialService {
    @Autowired
    MaterialRepository repository;
    public List<Material> getAll(){
        return repository.getAll();
    }

    public List<Material> getAllbyName(String name){
        return repository.searchByName('%'+name+'%');
    }
    public Material add(MaterialRequest request){
        Material material = new Material();
        material.setDescription(request.getDescription());
        material.setName(request.getName());
        material.setCreateDate(new Date());
        material.setStatus(0);
        return repository.save(material);
    }
    public Material update(Integer Id,MaterialRequest request){
        Material material = repository.getById(Id);
        material.setDescription(request.getDescription());
        material.setName(request.getName());
        material.setUpdateDate(new Date());
        return repository.save(material);
    }
    public Material delete(Integer Id){
        Material material = repository.getById(Id);
        material.setStatus(1);
        return repository.save(material);
    }
    public Material getById(Integer Id){
        Material material = repository.getById(Id);
        return material;
    }
    public Material restore(Integer Id){
        Material Material = repository.getById(Id);
        Material.setStatus(0);
        return repository.save(Material);
    }

    public Material deleteFake(Integer Id){
        Material Material = repository.getById(Id);
        Material.setStatus(1);
        return repository.save(Material);
    }

    public List<Material> getInActive() {
        return repository.getInActive();
    }
}
