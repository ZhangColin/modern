package com.cartisan.modern.transaction.domain;

import com.cartisan.modern.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Transactions {
    private final TransactionRepository repository;

    @Autowired
    public Transactions(TransactionRepository repository) {

        this.repository = repository;
    }

    public void add(Transaction transaction, Runnable afterSuccess) {
        repository.save(transaction);
        afterSuccess.run();
    }
}
