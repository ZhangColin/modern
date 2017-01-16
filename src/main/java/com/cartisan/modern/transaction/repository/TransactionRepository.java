package com.cartisan.modern.transaction.repository;

import com.cartisan.modern.transaction.domain.Transaction;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TransactionRepository extends Repository<Transaction, Long> {
    void save(Transaction transaction);

    List<Transaction> findAll();
}
