package com.poly.datn.service;

import com.poly.datn.model.TPointTransactions;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPointTransactionService {

    TPointTransactions saveTransaction(TPointTransactions transaction);

    TPointTransactions updateTransaction(Long id, TPointTransactions transaction);

    void deleteTransaction(Long id);

    List<TPointTransactions> getAllTransactions();

    TPointTransactions getTransactionById(Long id);


    Page<TPointTransactions> getAllTransactionsPaginated(int page, int size);
}
