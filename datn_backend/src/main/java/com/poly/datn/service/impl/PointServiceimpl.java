package com.poly.datn.service.impl;

import com.poly.datn.model.TPoints;
import com.poly.datn.repository.IPointRepository;
import com.poly.datn.service.IPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PointServiceimpl implements IPointService {

    @Autowired
    private IPointRepository pointRepository;

    @Override
    public TPoints save(TPoints tPoints) {
        return pointRepository.save(tPoints);
    }

    @Override
    public TPoints update(Long id, TPoints tPoints) {
        return pointRepository.save(tPoints);
    }

    @Override
    public void delete(Long id) {
        pointRepository.deleteById(id);
    }

    @Override
    public TPoints getById(Long id) {
        Optional<TPoints> result = pointRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public List<TPoints> getAll() {
        return pointRepository.findAll();
    }

    @Override
    public Page<TPoints> getAllPaginated(int page, int size) {
        return pointRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<TPoints> getPointsById(Long id) {
        return pointRepository.findById(id);
    }

    @Override
    public List<TPoints> getAllByStatus(int status) {
        return pointRepository.findByStatus(status);
    }
}
