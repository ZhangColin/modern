package com.cartisan.modern.transaction.repository;

import com.cartisan.modern.transaction.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;

@Transactional
public interface TransactionRepository extends Repository<Transaction, Long> {
    void save(Transaction transaction);

    Page<Transaction> findAll(Pageable pageable);
}
