package com.cartisan.modern.transaction.domain.summary;

import com.cartisan.modern.transaction.domain.Transaction;

public abstract class TransactionForSummary {
    public abstract int income();

    public abstract int outcome();

    public abstract int balance();

    public static TransactionForSummary create(Transaction transaction) {
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
