package com.cartisan.modern.transaction.builder;

import com.cartisan.modern.transaction.domain.Transaction;

import java.util.List;
import java.util.stream.Collectors;

import static com.cartisan.modern.common.Formats.parseDay;
import static com.cartisan.modern.transaction.domain.Transaction.Type.Income;
import static com.cartisan.modern.transaction.domain.Transaction.builder;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class TransactionBuilder {
    private int count;

    public static Transaction.TransactionBuilder defaultTransaction(){
        return builder()
                .type(Income)
                .description("description")
                .date(parseDay("2016-07-01"))
                .amount(100);
    }

    public static TransactionBuilder defaultTransactions(int count) {
        return new TransactionBuilder().count(count);
    }

    private TransactionBuilder count(int count) {
        this.count = count;
        return this;
    }

    public List<Transaction> buildAll() {
        return range(0, count).mapToObj(i->defaultTransaction().build()).collect(toList());
    }
}
