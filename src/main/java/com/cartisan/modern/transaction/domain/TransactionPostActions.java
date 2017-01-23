package com.cartisan.modern.transaction.domain;

import java.util.function.Consumer;

public interface TransactionPostActions {
    void withSummary(Consumer<SummaryOfTransactions> consumer);
}
