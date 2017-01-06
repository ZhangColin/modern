package com.cartisan.modern.transaction.domain;

import com.cartisan.modern.common.PostActions;
import com.cartisan.modern.common.PostActionsFactory;
import com.cartisan.modern.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.cartisan.modern.common.PostActionsFactory.failed;
import static com.cartisan.modern.common.PostActionsFactory.success;

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
            return success();
        }
        catch (IllegalArgumentException e){
            return failed();
        }
    }
}
