package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.common.page.PageView;
import com.cartisan.modern.common.page.PageableFactory;
import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.TransactionsPostActions;
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
    PresentableSummaryOfTransactions presentableSummaryOfTransactions = spy(defaultPresentableSummaryOfTransactions().build());
    PageableFactory mockPageableFactory = mock(PageableFactory.class);
    PageView mockPageView = mock(PageView.class);
    TransactionListController controller = new TransactionListController(
            mockTransactions, presentableTransactions, presentableSummaryOfTransactions,
            mockPageableFactory, mockPageView);

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
    public void should_pass_result_range_to_transactions() {
        Pageable pageable = defaultPageable().build();
        given_pageable_will_be_created_as(pageable);

        controller.index();

        verify(mockTransactions).processAll(any(Consumer.class), eq(pageable));
    }

    @Test
    public void should_let_view_display_total_page_count() {
        controller.index();

        verify(mockPageView).display(5);
    }

    private void given_pageable_will_be_created_as(Pageable pageable) {
        when(mockPageableFactory.create()).thenReturn(pageable);
    }

    private void given_transactions_processAll_will_return(final Transaction transaction, SummaryOfTransactions summaryOfTransactions) {
        when(mockTransactions.processAll(any(Consumer.class), any(Pageable.class))).thenAnswer(new Answer<TransactionsPostActions>() {
            @Override
            public TransactionsPostActions answer(InvocationOnMock invocation) throws Throwable {
                Consumer<Transaction> consumer = invocation.getArgumentAt(0, Consumer.class);
                consumer.accept(transaction);
                return stubTransactionsPostActionsWith(summaryOfTransactions);
            }
        });
    }

    private TransactionsPostActions stubTransactionsPostActionsWith(SummaryOfTransactions summaryOfTransactions) {
        TransactionsPostActions stubTransactionsPostActions = mock(TransactionsPostActions.class);
        doAnswer(invocation -> {
            Consumer<SummaryOfTransactions> consumer = invocation.getArgumentAt(0, Consumer.class);
            consumer.accept(summaryOfTransactions);
            return stubTransactionsPostActions;
        }).when(stubTransactionsPostActions).withSummary(any(Consumer.class));
        doAnswer(invocation -> {
            Consumer<Integer> consumer = invocation.getArgumentAt(0, Consumer.class);
            consumer.accept(5);
            return stubTransactionsPostActions;
        }).when(stubTransactionsPostActions).withTotalPageCount(any(Consumer.class));
        return stubTransactionsPostActions;
    }

    private ModelAndView list() {
        return controller.index();
    }
}
