package com.cartisan.modern.transaction.domain;

import com.cartisan.modern.transaction.domain.summary.SummaryOfTransactions;

import java.util.function.Consumer;

public interface TransactionsPostActions {
    TransactionsPostActions withSummary(Consumer<SummaryOfTransactions> consumer);

    TransactionsPostActions withTotalPageCount(Consumer<Integer> consumer);
}
