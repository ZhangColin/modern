package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.common.controller.PageableFactory;
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
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.ModelAndView;

import java.util.function.Consumer;

import static com.cartisan.modern.common.builder.PageableBuilder.defaultPageable;
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
    PageableFactory mockPageableFactory = mock(PageableFactory.class);
    TransactionListController controller = new TransactionListController(
            mockTransactions, presentableTransactions, presentableSummaryOfTransactions,
            mockPageableFactory);

    @Before
    public void given_transactions_processAll_is_ready_to_be_called() {
        given_transactions_processAll_will_return(transaction, summaryOfTransactions);
    }

    @Test
    public void should_display_view() {
        assertThat(list()).isInstanceOf(PresentableTransactions.class);
    }

    @Test
    public void should_let_view_display_transaction() {
        spyOnDisplayOf(presentableTransactions);

        list();
        verify(presentableTransactions).display(transaction);
    }

    @Test
    public void should_let_view_display_summary_of_transactions() {
        spyOnDisplayOf(presentableSummaryOfTransactions);

        list();

        verify(presentableSummaryOfTransactions).display(summaryOfTransactions);
    }

    @Test
    public void should_pass_result_range_to_transactions(){
        Pageable pageable = defaultPageable().build();
        int pageNumber = 1;
        given_result_range_will_be_created_with(pageable, pageNumber);

        controller.index(pageNumber);

        verify(mockTransactions).processAll(any(Consumer.class), eq(pageable));
    }

    private void given_result_range_will_be_created_with(Pageable pageable, int pageNumber) {
        when(mockPageableFactory.create(pageNumber)).thenReturn(pageable);
    }

    private void given_transactions_processAll_will_return(Transaction transaction, SummaryOfTransactions summaryOfTransactions) {
        when(mockTransactions.processAll(any(Consumer.class), any(Pageable.class))).thenAnswer(new Answer<TransactionPostActions>() {
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

    private ModelAndView list() {
        return controller.index(1);
    }
}
