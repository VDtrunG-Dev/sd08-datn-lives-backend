package com.poly.datn.service.impl;


import com.poly.datn.model.TRank;
import com.poly.datn.repository.IRankRepository;
import com.poly.datn.service.IRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RankServiceImpl implements IRankService {

    @Autowired
    private IRankRepository rankRepository;


    @Override
    public TRank createRank(TRank tRank) {
        return rankRepository.save(tRank);
    }

    @Override
    public TRank updateRank(Long id, TRank updateRank) {
        if (rankRepository.existsById(id)) {
            updateRank.setId(id);
            return rankRepository.save(updateRank);
        }

        return null;
    }

    @Override
    public boolean deleteRank(Long id) {
        if (rankRepository.existsById(id)) {
            rankRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<TRank> getRankById(Long id) {
        return rankRepository.findById(id);
    }

    @Override
    public List<TRank> getAllRank() {
        return rankRepository.findAll();
    }


    @Override
    public Page<TRank> getAllPaged(int page, int size) {
        return rankRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<TRank> getAllRanksByStatus(int status) {
        return rankRepository.findByStatus(status);
    }

    @Override
    public List<TRank> searchAll(String rankName, Integer minimumPoints, Integer status) {
        return rankRepository.findAll().stream()
                .filter(rank -> (rankName == null || (rank.getRankName() != null && rank.getRankName().contains(rankName))))

                .filter(rank -> minimumPoints == null || rank.getMinimumPoints() >= minimumPoints)
                .filter(rank -> status == null || rank.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    @Override
    public Page<TRank> getActiveRank(Integer status, Integer page) {
        return rankRepository.findRanksByStatus(status, PageRequest.of(page, 5));
    }

    @Override
    public Page<TRank> getInactiveRank(Integer status, Integer page) {
        return rankRepository.findRanksByStatus(status, PageRequest.of(page, 5));
    }


}
