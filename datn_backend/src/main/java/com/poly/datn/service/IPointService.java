package com.poly.datn.service;

import com.poly.datn.model.TPoints;
import com.poly.datn.model.TRank;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IPointService {

    TPoints save(TPoints tPoints);

    TPoints update(Long id, TPoints tPoints);

    void delete(Long id);

    TPoints getById(Long id);

    List<TPoints> getAll();

    Page<TPoints> getAllPaginated(int page, int size);

    Optional<TPoints> getPointsById(Long id);


    List<TPoints> getAllByStatus(int status);


}