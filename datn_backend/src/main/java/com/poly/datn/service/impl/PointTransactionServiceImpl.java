package com.poly.datn.service.impl;

import com.poly.datn.model.TPointTransactions;
import com.poly.datn.repository.IPointTransactionRepository;
import com.poly.datn.service.IPointTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointTransactionServiceImpl implements IPointTransactionService {

    @Autowired
    private IPointTransactionRepository pointTransactionRepository;

    @Override
    public TPointTransactions saveTransaction(TPointTransactions transaction) {
        return pointTransactionRepository.save(transaction);
    }

    @Override
    public TPointTransactions updateTransaction(Long id, TPointTransactions transaction) {
        if (!pointTransactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction not found!");
        }
        transaction.setTransactionId(id);
        return pointTransactionRepository.save(transaction);
    }


    @Override
    public void deleteTransaction(Long id) {
        if (!pointTransactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction not found!");
        }
        pointTransactionRepository.deleteById(id);
    }

    @Override
    public List<TPointTransactions> getAllTransactions() {
        return pointTransactionRepository.findAll();
    }

    @Override
    public TPointTransactions getTransactionById(Long id) {
        return pointTransactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found!"));
    }

    @Override
    public Page<TPointTransactions> getAllTransactionsPaginated(int page, int size) {
        return pointTransactionRepository.findAll(PageRequest.of(page, size));
    }
}
