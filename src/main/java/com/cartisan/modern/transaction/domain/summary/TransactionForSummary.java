package com.cartisan.modern.transaction.domain.summary;

import com.cartisan.modern.transaction.domain.Transaction;

public interface TransactionForSummary {
    int income();

    int outcome();

    int balance();

    static TransactionForSummary create(Transaction transaction) {
        switch (transaction.getType()) {
            case Income:
                return new IncomeTransactionForSummary(transaction);
            case Outcome:
                return new OutcomeTransactionForSummary(transaction);
            default:
                throw new IllegalStateException();
        }
    }
}
