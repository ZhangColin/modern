package com.cartisan.modern.transaction.domain;

import com.cartisan.modern.transaction.domain.summary.SummaryOfTransactions;

import java.util.function.Consumer;

public interface TransactionPostActions {
    void withSummary(Consumer<SummaryOfTransactions> consumer);
}
