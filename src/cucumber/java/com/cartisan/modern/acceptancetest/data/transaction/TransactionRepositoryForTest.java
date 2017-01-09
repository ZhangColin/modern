package com.cartisan.modern.acceptancetest.data.transaction;

import com.cartisan.modern.transaction.domain.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Scope("cucumber-glue")
public interface TransactionRepositoryForTest extends Repository<Transaction, Long> {
    List<Transaction> findAll();

    void deleteAll();

    void save(Transaction transaction);
}
