package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.TransactionPostActions;
import com.cartisan.modern.transaction.domain.Transactions;
import com.cartisan.modern.transaction.domain.summary.SummaryOfTransactions;
import com.cartisan.modern.transaction.view.PresentableSummaryOfTransactions;
import com.cartisan.modern.transaction.view.PresentableTransactions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.function.Consumer;

import static com.cartisan.modern.common.controller.ControllerTestHelper.spyOnDisplayOf;
import static com.cartisan.modern.transaction.builder.PresentableSummaryOfTransactionsBuilder.defaultPresentableSummaryOfTransactions;
import static com.cartisan.modern.transaction.builder.PresentableTransactionsBuilder.defaultPresentableTransactions;
import static com.cartisan.modern.transaction.builder.SummaryOfTransactionsBuilder.builder;
import static com.cartisan.modern.transaction.builder.TransactionBuilder.defaultTransaction;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TransactionListControllerTest {
    SummaryOfTransactions summaryOfTransactions = builder().build();
    Transaction transaction = defaultTransaction().build();
    Transactions mockTransactions = mock(Transactions.class);
    PresentableTransactions presentableTransactions = spy(defaultPresentableTransactions().build());
    PresentableSummaryOfTransactions presentableSummaryOfTransactions =
            spy(defaultPresentableSummaryOfTransactions().build());

    TransactionListController controller = new TransactionListController(mockTransactions, presentableTransactions, presentableSummaryOfTransactions);

    @Before
    public void given_transactions_processAll_is_ready_to_be_called() {
        given_transactions_processAll_will_return(transaction, summaryOfTransactions);
    }

    @Test
    public void should_display_view() {
        assertThat(controller.index()).isInstanceOf(PresentableTransactions.class);
    }

    @Test
    public void should_let_view_display_transaction() {
        spyOnDisplayOf(presentableTransactions);

        controller.index();
        verify(presentableTransactions).display(transaction);
    }

    @Test
    public void should_let_view_display_summary_of_transactions() {
        spyOnDisplayOf(presentableSummaryOfTransactions);

        controller.index();

        verify(presentableSummaryOfTransactions).display(summaryOfTransactions);
    }

    private void given_transactions_processAll_will_return(Transaction transaction, SummaryOfTransactions summaryOfTransactions) {
        when(mockTransactions.processAll(any(Consumer.class))).thenAnswer(new Answer<TransactionPostActions>() {
            @Override
            public TransactionPostActions answer(InvocationOnMock invocation) throws Throwable {
                Consumer<Transaction> consumer = invocation.getArgumentAt(0, Consumer.class);
                consumer.accept(transaction);
                return stubTransactionsPostActionsWith(summaryOfTransactions);
            }
        });
    }

    private TransactionPostActions stubTransactionsPostActionsWith(SummaryOfTransactions summaryOfTransactions) {
        TransactionPostActions stubTransactionsPostActions = mock(TransactionPostActions.class);
        doAnswer(invocation -> {
            Consumer<SummaryOfTransactions> consumer = invocation.getArgumentAt(0, Consumer.class);
            consumer.accept(summaryOfTransactions);
            return null;
        }).when(stubTransactionsPostActions).withSummary(any(Consumer.class));
        return stubTransactionsPostActions;
    }
}
