package com.cartisan.modern.transaction.domain.summary;

import com.cartisan.modern.transaction.domain.Transaction;

public class OutcomeTransactionForSummary implements TransactionForSummary {
    private final Transaction origin;

    public OutcomeTransactionForSummary(Transaction transaction) {
        origin = transaction;
    }

    public int income() {
        return 0;
    }

    public int outcome() {
        return origin.getAmount();
    }

    public int balance() {
        return -origin.getAmount();
    }
}
