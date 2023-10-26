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
        return Optional.empty();
    }

    @Override
    public List<TRank> getAllRank() {
        return rankRepository.findAll();
    }


    @Override
    public Page<TRank> getAllPaged(int page, int size) {
        return rankRepository.findAll(PageRequest.of(page, size));
    }


}
