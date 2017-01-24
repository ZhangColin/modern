package com.cartisan.modern.transaction.domain.summary;

import com.cartisan.modern.transaction.domain.Transaction;

public class OutcomeTransactionForSummary extends TransactionForSummary {
    private final Transaction origin;

    public OutcomeTransactionForSummary(Transaction transaction) {
        origin = transaction;
    }

    @Override
    public int outcome() {
        return origin.getAmount();
    }

    @Override
    public int balance() {
        return -origin.getAmount();
    }
}
