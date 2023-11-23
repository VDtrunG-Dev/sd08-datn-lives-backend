package com.spring.shop.service;


import com.spring.shop.entity.RetunExchange;
import com.spring.shop.repository.ReturnExChangeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnEXChangeServer {

    @Autowired
    ReturnExChangeRepository repo;

    public List<RetunExchange> getAll(){
        return repo.getAll();
    }
    public RetunExchange getRetunExchangeById(Integer id){
        return repo.getRetunExchangeById(id);
    }

    public List<RetunExchange> getAllDateDESC(){
        return repo.getRetunExchangeByOrderByCreateDateDesc();
    }
    public List<RetunExchange> getAllDateASC(){
        return repo.getRetunExchangeByOrderByCreateDateAsc();
    }
}
