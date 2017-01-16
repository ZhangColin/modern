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
        consumer.accept(transaction(Income, "Course Registration", parseDay("2016-08-14"), 4000));
        consumer.accept(transaction(Outcome, "Buy MacBook Pro", parseDay("2015-11-01"), 100));
    }

    private Transaction transaction(Transaction.Type type, String description, Date date, int amount) {
        Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setAmount(amount);

        return transaction;
    }
}
