package com.cartisan.modern.transaction.domain.summary;

import com.cartisan.modern.transaction.domain.Transaction;

public class IncomeTransactionForSummary implements TransactionForSummary {
    private final Transaction origin;

    public IncomeTransactionForSummary(Transaction transaction) {
        origin = transaction;
    }

    public int income() {
        return origin.getAmount();
    }

    public int outcome() {
        return 0;
    }

    public int balance() {
        return origin.getAmount();
    }
}
