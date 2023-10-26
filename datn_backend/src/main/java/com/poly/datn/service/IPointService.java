package com.poly.datn.service;

import com.poly.datn.model.TPoints;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPointService {

    TPoints save(TPoints tPoints);

    TPoints update(Long id, TPoints tPoints);

    void delete(Long id);

    TPoints getById(Long id);

    List<TPoints> getAll();

    Page<TPoints> getAllPaginated(int page, int size);
}
