package com.cartisan.modern.transaction.view;

import com.cartisan.modern.transaction.domain.SummaryOfTransactions;
import org.junit.Test;

import java.util.AbstractMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PresentableSummaryOfTransactionsTest {
    private int balance = 100;

    @Test
    public void should_show_balance(){
        PresentableSummaryOfTransactions presentableSummaryOfTransactions = new PresentableSummaryOfTransactions();
        SummaryOfTransactions mockSummaryOfTransactions = mock(SummaryOfTransactions.class);
        when(mockSummaryOfTransactions.balance()).thenReturn(balance);

        presentableSummaryOfTransactions.display(mockSummaryOfTransactions);

        assertThat(presentableSummaryOfTransactions.getModelMap())
                .containsExactly(new AbstractMap.SimpleEntry<>("balance", balance));
    }
}
