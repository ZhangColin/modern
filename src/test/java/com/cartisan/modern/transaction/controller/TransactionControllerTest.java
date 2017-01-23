package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.common.callback.PostActions;
import com.cartisan.modern.common.view.Message;
import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.Transactions;
import com.cartisan.modern.transaction.view.PresentableAddTransaction;
import com.cartisan.modern.transaction.view.PresentableTransactions;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import static com.cartisan.modern.common.callback.PostActionsFactory.failed;
import static com.cartisan.modern.common.callback.PostActionsFactory.success;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class TransactionControllerTest {
    Transactions mockTransactions = mock(Transactions.class);
    PresentableTransactions presentableTransactions = new PresentableTransactions(mockTransactions, "whatever message");
    Message mockMessage = mock(Message.class);
    TransactionController controller = new TransactionController(
            mockTransactions, new PresentableAddTransaction(), presentableTransactions, mockMessage);
    Transaction transaction = new Transaction();
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

            verify(mockMessage).display("a success message");
        }

    }
    public class AddSubmitFailed {

        @Test
        public void should_display_failed_message() {
            given_add_transaction_will(failed());
            controller.failedMessage = "a failed message";

            submitTransactionAdd(transaction);

            verify(mockMessage).display("a failed message");
        }
    }
    public class Valid {

        @Before
        public void given_has_some_failed_error() {
            given_has_field_error(true);
        }
        @Test
        public void should_not_add_transaction() {
            submitTransactionAdd(transaction);

            verify(mockTransactions, never()).add(transaction);
        }

        @Test
        public void should_display_view(){
            assertThat(submitTransactionAdd(transaction)).isInstanceOf(PresentableAddTransaction.class);
        }

    }

    public class List{
        @Test
        public void should_display_view(){
            assertThat(controller.index()).isInstanceOf(PresentableTransactions.class);
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
