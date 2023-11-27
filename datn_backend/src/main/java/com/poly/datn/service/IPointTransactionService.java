package com.poly.datn.service;

import com.poly.datn.model.TPointTransactions;
import com.poly.datn.model.TRank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IPointTransactionService {


    List<TPointTransactions> getAllTransactions();


    Page<TPointTransactions> getAllTransactionsPaginated(int page, int size);

    Optional<TPointTransactions> getPointTransactionsById(Long id);

    TPointTransactions savePointTransaction(TPointTransactions pointTransaction);

    TPointTransactions updatePointTransaction(Long id, TPointTransactions pointTransaction);

    boolean deletePointTransactionById(Long id);

    boolean existsById(Long id);


    List<TPointTransactions> getAllByStatus(int status);

    Page<TPointTransactions> getAllByStatusPaged(int status, int page, int size);

    List<TPointTransactions> searchAll(Long customerId, Integer transactionType, Date transactionDate, BigDecimal transactionAmount);

    List<TPointTransactions> searchByKeyword(String keyword);
}
