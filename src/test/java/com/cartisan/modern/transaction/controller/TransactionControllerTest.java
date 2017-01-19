package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.common.callback.PostActions;
import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.Transactions;
import com.cartisan.modern.transaction.view.PresentableAddTransaction;
import com.cartisan.modern.transaction.view.PresentableTransactions;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.function.Consumer;

import static com.cartisan.modern.common.callback.PostActionsFactory.failed;
import static com.cartisan.modern.common.callback.PostActionsFactory.success;
import static com.cartisan.modern.common.controller.Urls.TRANSACTION_ADD;
import static com.cartisan.modern.common.controller.Urls.TRANSACTION_INDEX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class TransactionControllerTest {
    Transactions mockTransactions = mock(Transactions.class);
    PresentableAddTransaction mockPresentableAddTransaction = mock(PresentableAddTransaction.class);
    PresentableTransactions mockPresentableTransactions = mock(PresentableTransactions.class);
    TransactionController controller = new TransactionController(mockTransactions, mockPresentableAddTransaction, mockPresentableTransactions);
    Model mockModel = mock(Model.class);
    Transaction transaction = new Transaction();
    BindingResult stubBindingResult = mock(BindingResult.class);

    @Before
    public void given_has_no_field_error() {
        given_has_field_error(false);
    }

    public class Add {

        @Test
        public void should_go_to_transaction_add_page() {
            assertThat(addTransaction()).isEqualTo(TRANSACTION_ADD);
        }

        @Test
        public void should_display_view() {
            addTransaction();

            verifyPresentableAddTransactionDisplay();
        }

        private String addTransaction() {
            return controller.addTransaction(mockModel);
        }
    }

    public class AddSubmitSuccess {

        @Before
        public void given_add_transaction_will_success() {
            given_add_transaction_will(success());
        }
        @Test
        public void should_go_to_transaction_add_page() {
            assertThat(submitTransactionAdd(transaction)).isEqualTo(TRANSACTION_ADD);
        }

        @Test
        public void should_display_view() {
            submitTransactionAdd(transaction);

            verifyPresentableAddTransactionDisplay();
        }

        @Test
        public void should_add_transaction() {
            submitTransactionAdd(transaction);

            verify(mockTransactions).add(eq(transaction));
        }

        @Test
        public void should_return_add_success_message_to_page() {
            controller.successMessage = "a success message";

            submitTransactionAdd(transaction);

            verify(mockModel).addAttribute("message", "a success message");
        }

    }
    public class AddSubmitFailed {

        @Test
        public void should_return_add_failed_message_to_page() {
            given_add_transaction_will(failed());
            controller.failedMessage = "a failed message";

            submitTransactionAdd(transaction);

            verify(mockModel).addAttribute("message", "a failed message");
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
        public void should_go_to_add_transaction_page() {
            assertThat(submitTransactionAdd(transaction)).isEqualTo(TRANSACTION_ADD);
        }

        @Test
        public void should_display_view(){
            submitTransactionAdd(transaction);

            verifyPresentableAddTransactionDisplay();
        }

    }

    public class List {
        @Test
        public void should_go_to_transaction_list_page(){
            assertThat(showAllTransactions()).isEqualTo(TRANSACTION_INDEX);
        }

        @Test
        public void should_show_all_transactions(){
            given_exists_transactions(transaction);

            showAllTransactions();

            verify(mockPresentableTransactions).display();
        }

        private void given_exists_transactions(Transaction transaction) {
            doAnswer(invocation->{
                Consumer<Transaction> consumer = (Consumer<Transaction>) invocation.getArguments()[0];
                consumer.accept(transaction);
                return null;
            }).when(mockTransactions).processAll(any(Consumer.class));
        }

        private String showAllTransactions() {
            return controller.index();
        }
    }

    private void given_has_field_error(boolean value) {
        when(stubBindingResult.hasFieldErrors()).thenReturn(value);
    }

    private String submitTransactionAdd(Transaction transaction) {
        return controller.submitAddTransaction(transaction, stubBindingResult, mockModel);
    }

    private void given_add_transaction_will(PostActions postActions) {
        when(mockTransactions.add(any(Transaction.class))).thenReturn(postActions);
    }

    private void verifyPresentableAddTransactionDisplay() {
        verify(mockPresentableAddTransaction).display(mockModel, Transaction.Type.values());
    }
}
