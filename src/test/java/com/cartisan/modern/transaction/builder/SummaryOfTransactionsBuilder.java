package com.cartisan.modern.transaction.builder;

import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.summary.SummaryOfTransactions;

import java.util.ArrayList;
import java.util.List;

import static com.cartisan.modern.transaction.builder.TransactionBuilder.defaultTransaction;
import static com.cartisan.modern.transaction.domain.Transaction.Type.Income;
import static com.cartisan.modern.transaction.domain.Transaction.Type.Outcome;

public class SummaryOfTransactionsBuilder {
    private final List<Transaction> all = new ArrayList<>();

    public static SummaryOfTransactionsBuilder builder(){
        return new SummaryOfTransactionsBuilder();
    }

    public SummaryOfTransactionsBuilder incomeWithAmount(int amount) {
        addTransaction(amount, Income);
        return this;
    }

    public SummaryOfTransactionsBuilder outcomeWithAmount(int amount) {
        addTransaction(amount, Outcome);
        return this;
    }

    public SummaryOfTransactions build(){
        return new SummaryOfTransactions(all);
    }

    private boolean addTransaction(int amount, Transaction.Type type) {
        return all.add(defaultTransaction().type(type).amount(amount).build());
    }
}
