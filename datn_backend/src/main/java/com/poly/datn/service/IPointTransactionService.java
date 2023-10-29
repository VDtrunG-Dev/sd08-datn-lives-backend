package com.poly.datn.service;

import com.poly.datn.model.TPointTransactions;
import com.poly.datn.model.TRank;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IPointTransactionService {


    List<TPointTransactions> getAllTransactions();



    Page<TPointTransactions> getAllTransactionsPaginated(int page, int size);

    Optional<TPointTransactions> getPointTransactionsById(Long id);

    TPointTransactions savePointTransaction(TPointTransactions pointTransaction);

    TPointTransactions updatePointTransaction(TPointTransactions pointTransaction);

    boolean deletePointTransactionById(Long id);

    boolean existsById(Long id);


    List<TPointTransactions> getAllByStatus(int status);
}
