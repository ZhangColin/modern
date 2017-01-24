package com.cartisan.modern.transaction.view;

import com.cartisan.modern.transaction.domain.summary.SummaryOfTransactions;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PresentableSummaryOfTransactionsTest {
    private PresentableSummaryOfTransactions presentableSummaryOfTransactions;
    private SummaryOfTransactions mockSummaryOfTransactions = mock(SummaryOfTransactions.class);

    @Test
    public void should_show_balance(){
        presentableSummaryOfTransactions = new PresentableSummaryOfTransactions(
                "Balance is %s",
                "whatever total income message",
                "whatever total outcome message"
        );
        given_balance_is(100);

        presentableSummaryOfTransactions.display(mockSummaryOfTransactions);

        assertThat(modelOfPresentableSummaryOfTransactions()).containsEntry("balance", "Balance is 100");
    }

    @Test
    public void should_show_total_income(){
        presentableSummaryOfTransactions = new PresentableSummaryOfTransactions(
                "whatever balance message",
                "Total income is %s",
                "whatever total outcome message"
        );
        given_total_income_is(100);

        presentableSummaryOfTransactions.display(mockSummaryOfTransactions);

        assertThat(modelOfPresentableSummaryOfTransactions()).containsEntry("totalIncome", "Total income is 100");
    }

    private void given_total_income_is(int amount) {
        when(mockSummaryOfTransactions.totalIncome()).thenReturn(amount);
    }

    @Test
    public void should_show_total_outcome(){
        presentableSummaryOfTransactions = new PresentableSummaryOfTransactions(
                "whatever balance message",
                "whatever total income message",
                "Total outcome is %s"
        );
        given_total_outcome_is(100);

        presentableSummaryOfTransactions.display(mockSummaryOfTransactions);

        assertThat(modelOfPresentableSummaryOfTransactions()).containsEntry("totalOutcome", "Total outcome is 100");
    }

    private void given_total_outcome_is(int amount) {
        when(mockSummaryOfTransactions.totalOutcome()).thenReturn(amount);
    }

    private ModelMap modelOfPresentableSummaryOfTransactions() {
        return presentableSummaryOfTransactions.getModelMap();
    }

    private void given_balance_is(int amount) {
        when(mockSummaryOfTransactions.balance()).thenReturn(amount);
    }
}
