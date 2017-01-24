package com.cartisan.modern.transaction.builder;

import com.cartisan.modern.transaction.domain.Transaction;

import static com.cartisan.modern.common.Formats.parseDay;
import static com.cartisan.modern.transaction.domain.Transaction.Type.Income;
import static com.cartisan.modern.transaction.domain.Transaction.builder;

public class TransactionBuilder {
    public static Transaction.TransactionBuilder defaultTransaction(){
        return builder()
                .type(Income)
                .description("description")
                .date(parseDay("2016-07-01"))
                .amount(100);
    }
}
