package com.cartisan.modern.transaction.builder;

import com.cartisan.modern.transaction.view.PresentableTransactions;

import static com.cartisan.modern.transaction.view.PresentableTransactions.builder;

public class PresentableTransactionsBuilder {
    public static PresentableTransactions.PresentableTransactionsBuilder defaultPresentableTransactions() {
        return builder().noTransactionMessage("whatever message");
    }
}
