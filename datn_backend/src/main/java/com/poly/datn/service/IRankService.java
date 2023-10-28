package com.poly.datn.service;

import com.poly.datn.model.TRank;

import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;

public interface IRankService {

    TRank createRank(TRank tRank);

    TRank updateRank(Long id, TRank updateRank);

    boolean deleteRank(Long id);

    Optional<TRank> getRankById(Long id);

    List<TRank> getAllRank();

    Page<TRank> getAllPaged(int page, int size);

    List<TRank> getAllRanksByStatus(int status);


    List<TRank> searchAll(String rankName, Integer minimumPoints, Integer status);


    Page<TRank> getActiveRank(Integer status, Integer page);

    Page<TRank> getInactiveRank(Integer status, Integer page);


    Page<TRank> getAllByStatusPaged(int status, int page, int size);



}
