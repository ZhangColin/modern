package com.cartisan.modern.transaction.domain;

import com.cartisan.modern.common.callback.PostActions;
import com.cartisan.modern.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Consumer;

import static com.cartisan.modern.common.Formats.parseDay;
import static com.cartisan.modern.common.callback.PostActionsFactory.failed;
import static com.cartisan.modern.common.callback.PostActionsFactory.success;
import static com.cartisan.modern.transaction.domain.Transaction.Type.Income;
import static com.cartisan.modern.transaction.domain.Transaction.Type.Outcome;

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

    public void processAll(Consumer<Transaction> consumer) {
        repository.findAll().forEach(consumer::accept);
    }
}
