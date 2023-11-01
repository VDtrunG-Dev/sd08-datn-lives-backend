package com.poly.datn.service.impl;

import com.poly.datn.model.TPointTransactions;
import com.poly.datn.repository.IPointTransactionRepository;
import com.poly.datn.service.IPointTransactionService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PointTransactionServiceImpl implements IPointTransactionService {

    @Autowired
    private IPointTransactionRepository pointTransactionRepository;


    @Override
    public List<TPointTransactions> getAllTransactions() {
        return pointTransactionRepository.findAll();
    }


    @Override
    public Page<TPointTransactions> getAllTransactionsPaginated(int page, int size) {
        return pointTransactionRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<TPointTransactions> getPointTransactionsById(Long id) {
        return pointTransactionRepository.findById(id);
    }

    @Override
    public TPointTransactions savePointTransaction(TPointTransactions pointTransaction) {
        return pointTransactionRepository.save(pointTransaction);
    }

    @Override
    public TPointTransactions updatePointTransaction(Long id, TPointTransactions pointTransaction) {
        if (pointTransactionRepository.existsById(pointTransaction.getTransactionId())) {
            return pointTransactionRepository.save(pointTransaction);
        }
        return null;
    }

    @Override
    public boolean deletePointTransactionById(Long id) {
        if (pointTransactionRepository.existsById(id)) {
            pointTransactionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(Long id) {
        return pointTransactionRepository.existsById(id);
    }

    @Override
    public List<TPointTransactions> getAllByStatus(int status) {
        return pointTransactionRepository.findByStatus(status);
    }

    @Override
    public Page<TPointTransactions> getAllByStatusPaged(int status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pointTransactionRepository.findAllByStatus(status, pageable);
    }

    @Override
    public List<TPointTransactions> searchAll(String transactionName, Integer minimumPoints, Integer status) {
        return pointTransactionRepository.findAll((Specification<TPointTransactions>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (transactionName != null && !transactionName.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("transactionName"), "%" + transactionName + "%"));
            }

            if (minimumPoints != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("points"), minimumPoints));
            }

            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }


}
