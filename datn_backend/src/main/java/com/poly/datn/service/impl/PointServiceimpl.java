package com.poly.datn.service.impl;

import com.poly.datn.model.TPoints;
import com.poly.datn.repository.IPointRepository;
import com.poly.datn.service.IPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    @Override
    public Page<TPoints> getAllByStatusPaged(int status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pointRepository.findAllByStatus(status, pageable);
    }

    @Override
    public List<TPoints> searchAll(Long customerId, Date transactionDate, BigDecimal transactionAmount, Integer pointsEarned) {
        return pointRepository.findAll().stream()
                .filter(points -> points.getCustomer().getId().equals(customerId))
                .filter(points -> points.getTransactionDate().equals(transactionDate))
                .filter(points -> points.getTransactionAmount().equals(transactionAmount))
                .filter(points -> points.getPointsEarned().equals(pointsEarned))
                .collect(Collectors.toList());
    }

    @Override
    public List<TPoints> searchByKeyword(String keyword) {
        return pointRepository.findAll().stream()
                .filter(points -> points.getCustomer().getLastName().contains(keyword)
                        || points.getTransactionAmount().toString().contains(keyword)
                        || points.getPointsEarned().toString().contains(keyword))
                .collect(Collectors.toList());
    }

}
