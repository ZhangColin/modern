package com.cartisan.modern.transaction.domain;

import com.cartisan.modern.common.callback.PostActions;
import com.cartisan.modern.common.controller.Pageable;
import com.cartisan.modern.transaction.domain.summary.SummaryOfTransactions;
import com.cartisan.modern.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

import static com.cartisan.modern.common.callback.PostActionsFactory.failed;
import static com.cartisan.modern.common.callback.PostActionsFactory.success;

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

    public TransactionPostActions processAll(Consumer<Transaction> consumer, Pageable pageable) {
        List<Transaction> all = repository.findAll();
        all.forEach(consumer::accept);
        return new SummaryOfTransactions(all);
    }
}
