package com.cartisan.modern.transaction.domain;

import com.cartisan.modern.common.callback.PostActions;
import com.cartisan.modern.transaction.domain.summary.SummaryOfTransactions;
import com.cartisan.modern.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        } catch (IllegalArgumentException e) {
            return failed();
        }
    }

    public TransactionsPostActions processAll(Consumer<Transaction> consumer, Pageable pageable) {
        Page<Transaction> all = repository.findAll(pageable);
        all.forEach(consumer::accept);

        return new TransactionsPostActions() {
            @Override
            public TransactionsPostActions withSummary(Consumer<SummaryOfTransactions> consumer) {
                consumer.accept(new SummaryOfTransactions(all.getContent()));
                return this;
            }

            @Override
            public TransactionsPostActions withTotalPageCount(Consumer<Integer> consumer) {
                consumer.accept(all.getTotalPages());
                return this;
            }
        };
    }
}
