package com.cartisan.modern.transaction.builder;

import com.cartisan.modern.transaction.view.PresentableSummaryOfTransactions;
import static com.cartisan.modern.transaction.view.PresentableSummaryOfTransactions.builder;

public class PresentableSummaryOfTransactionsBuilder {
    public static PresentableSummaryOfTransactions.PresentableSummaryOfTransactionsBuilder defaultPresentableSummaryOfTransactions(){
        return builder()
                .balanceMessage("whatever message")
                .totalIncomeMessage("whatever message")
                .totalOutcomeMessage("whatever message");
    }
}
