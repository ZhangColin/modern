package com.cartisan.modern.transaction.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.cartisan.modern.common.Formats.parseDay;
import static com.cartisan.modern.transaction.domain.Transaction.Type.Income;
import static com.cartisan.modern.transaction.domain.Transaction.Type.Outcome;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class SummaryOfTransactionsTest {
    private final Date anyDay = parseDay("2016-07-01");
    private final Date anyOtherDay = parseDay("2016-10-01");
    private final int whateverOutcomeAmount = 1000;
    private final int whateverIncomeAmount = 2000;

    @Test
    public void total_of_income(){
        SummaryOfTransactions summaryOfTransactions =
                new SummaryOfTransactions(allIncomeTransactionsWithAmounts(100, 200));
        assertThat(summaryOfTransactions.totalIncome()).isEqualTo(300);
    }

    @Test
    public void total_of_income_not_including_any_outcome(){
        SummaryOfTransactions summaryOfTransactions =
                new SummaryOfTransactions(incomeTransactionsWithAmountsAndWhateverOutcome(
                        100, 200, whateverOutcomeAmount));
        assertThat(summaryOfTransactions.totalIncome()).isEqualTo(300);
    }

    private List<Transaction> incomeTransactionsWithAmountsAndWhateverOutcome(int amount, int anotherAmount, int whateverAmount) {
        List<Transaction> transactions = allIncomeTransactionsWithAmounts(amount, anotherAmount);
        transactions.add(transaction(Outcome, "any description", anyDay, whateverAmount));
        return transactions;
    }

    private List<Transaction> allIncomeTransactionsWithAmounts(int amount, int otherAmount) {
        return new ArrayList<>(asList(
                transaction(Income, "any description", anyDay, amount),
                transaction(Income, "any other description", anyOtherDay, otherAmount)));
    }

    @Test
    public void total_of_outcome() {
        SummaryOfTransactions summaryOfTransactions =
                new SummaryOfTransactions(allOutcomeTransactionWithAmount(100, 200));
        assertThat(summaryOfTransactions.totalOutcome()).isEqualTo(300);
    }

    @Test
    public void total_of_outcome_not_including_any_income(){
        SummaryOfTransactions summaryOfTransactions = new SummaryOfTransactions(
                outcomeTransactionsWithAmountsAndWhateverIncome(100, 200, whateverIncomeAmount));

        assertThat(summaryOfTransactions.totalOutcome()).isEqualTo(300);
    }

    private List<Transaction> outcomeTransactionsWithAmountsAndWhateverIncome(int amount, int anotherAmount, int whateverAmount) {
        List<Transaction> transactions = allOutcomeTransactionWithAmount(amount, anotherAmount);
        transactions.add(transaction(Income, "any description", anyDay, whateverAmount));
        return transactions;
    }

    private List<Transaction> allOutcomeTransactionWithAmount(int amount, int otherAmount) {
        return new ArrayList<>(asList(
                transaction(Outcome, "any description", anyDay, amount),
                transaction(Outcome, "any other description", anyOtherDay, otherAmount)));
    }

    @Test
    public void balance(){
        SummaryOfTransactions summaryOfTransactions = new SummaryOfTransactions(asList(
                transaction(Income, "any description", anyDay, 200),
                transaction(Outcome, "any other description", anyOtherDay, 50)
        ));

        assertThat(summaryOfTransactions.balance()).isEqualTo(150);
    }

    private Transaction transaction(Transaction.Type type, String description, Date date, int amount) {
        Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setDate(date);
        transaction.setAmount(amount);
        return transaction;
    }
}
