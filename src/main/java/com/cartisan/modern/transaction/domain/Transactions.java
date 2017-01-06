package com.cartisan.modern.transaction.domain;

import com.cartisan.modern.common.PostActions;
import com.cartisan.modern.common.PostActionsFactory;
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

    public PostActions add(Transaction transaction) {
        try {
            repository.save(transaction);
            return PostActionsFactory.success();
        }
        catch (IllegalArgumentException e){
            return PostActionsFactory.failed();
        }
    }
}
