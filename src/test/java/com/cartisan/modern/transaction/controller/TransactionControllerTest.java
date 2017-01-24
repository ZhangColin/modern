package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.common.callback.PostActions;
import com.cartisan.modern.common.view.Message;
import com.cartisan.modern.common.view.View;
import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.TransactionPostActions;
import com.cartisan.modern.transaction.domain.Transactions;
import com.cartisan.modern.transaction.domain.summary.SummaryOfTransactions;
import com.cartisan.modern.transaction.view.PresentableAddTransaction;
import com.cartisan.modern.transaction.view.PresentableSummaryOfTransactions;
import com.cartisan.modern.transaction.view.PresentableTransactions;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.function.Consumer;

import static com.cartisan.modern.common.callback.PostActionsFactory.failed;
import static com.cartisan.modern.common.callback.PostActionsFactory.success;
import static com.cartisan.modern.common.controller.ControllerTestHelper.spyOnDisplayOf;
import static com.cartisan.modern.transaction.builder.TransactionBuilder.defaultTransaction;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class TransactionControllerTest {
    Transactions mockTransactions = mock(Transactions.class);
    PresentableTransactions presentableTransactions =
            spy(new PresentableTransactions("whatever message"));
    PresentableSummaryOfTransactions presentableSummaryOfTransactions =
            spy(new PresentableSummaryOfTransactions("whatever message",
                    "whatever message", "whatever message"));
    View mockView = mock(View.class);
    TransactionController controller = new TransactionController(
            mockTransactions, new PresentableAddTransaction(),
            presentableTransactions, presentableSummaryOfTransactions, mockView);
    Transaction transaction = defaultTransaction().build();
    BindingResult stubBindingResult = mock(BindingResult.class);

    @Before
    public void given_has_no_field_error() {
        given_has_field_error(false);
    }

    public class Add {
        @Test
        public void should_display_view() {
            assertThat(controller.addTransaction()).isInstanceOf(PresentableAddTransaction.class);
        }
    }

    public class AddSubmitSuccess {

        @Before
        public void given_add_transaction_will_success() {
            given_add_transaction_will(success());
        }

        @Test
        public void should_display_view() {
            assertThat(submitTransactionAdd(transaction)).isInstanceOf(PresentableAddTransaction.class);
        }

        @Test
        public void should_add_transaction() {
            submitTransactionAdd(transaction);

            verify(mockTransactions).add(eq(transaction));
        }

        @Test
        public void should_display_success_message() {
            controller.successMessage = "a success message";

            submitTransactionAdd(transaction);

            verify(mockView).display("a success message");
        }

    }

    public class AddSubmitFailed {

        @Test
        public void should_display_failed_message() {
            given_add_transaction_will(failed());
            controller.failedMessage = "a failed message";

            submitTransactionAdd(transaction);

            verify(mockView).display("a failed message");
        }
    }

    public class Valid {
        Transaction invalidTransaction = invalidTransaction();

        @Before
        public void given_has_some_failed_error() {
            given_has_field_error(true);
        }

        @Test
        public void should_not_add_transaction() {
            submitTransactionAdd(invalidTransaction);

            verify(mockTransactions, never()).add(invalidTransaction);
        }

        @Test
        public void should_display_view() {
            assertThat(submitTransactionAdd(invalidTransaction)).isInstanceOf(PresentableAddTransaction.class);
        }

        private Transaction invalidTransaction() {
            return defaultTransaction().type(null).description(null).date(null).amount(null).build();
        }
    }

    public class List {
        SummaryOfTransactions summaryOfTransactions = new SummaryOfTransactions(asList());

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

    private void given_has_field_error(boolean value) {
        when(stubBindingResult.hasFieldErrors()).thenReturn(value);
    }

    private ModelAndView submitTransactionAdd(Transaction transaction) {
        return controller.submitAddTransaction(transaction, stubBindingResult);
    }

    private void given_add_transaction_will(PostActions postActions) {
        when(mockTransactions.add(any(Transaction.class))).thenReturn(postActions);
    }
}
