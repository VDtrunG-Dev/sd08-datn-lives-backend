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


}
