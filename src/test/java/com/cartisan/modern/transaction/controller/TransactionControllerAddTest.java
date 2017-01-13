package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.common.PostActions;
import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.Transactions;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static com.cartisan.modern.Urls.TRANSACTION_ADD;
import static com.cartisan.modern.common.PostActionsFactory.failed;
import static com.cartisan.modern.common.PostActionsFactory.success;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class TransactionControllerAddTest {
    Transactions mockTransactions = mock(Transactions.class);
    TransactionController controller = new TransactionController(mockTransactions);
    Model mockModel = mock(Model.class);
    Transaction transaction = new Transaction();
    BindingResult anyBindingResult = mock(BindingResult.class);

    @Test
    public void go_to_transaction_add_page(){
        assertThat(controller.addTransaction(mockModel)).isEqualTo(TRANSACTION_ADD);
    }

    @Test
    public void show_all_transaction_types(){
        controller.addTransaction(mockModel);

        verify(mockModel).addAttribute("types", Transaction.Type.values());
    }

    @Test
    public void back_page_after_submit() {
        given_add_transaction_will(success());
        assertThat(controller.submitAddTransaction(transaction, anyBindingResult, mockModel)).isEqualTo(TRANSACTION_ADD);
    }

    @Test
    public void show_all_transaction_types_after_submit(){
        given_add_transaction_will(success());

        submitAddTransaction();

        verify(mockModel).addAttribute("types", Transaction.Type.values());
    }

    @Test
    public void add_transaction() {
        given_add_transaction_will(success());

        submitAddTransaction();

        verify(mockTransactions).add(eq(transaction));
    }

    @Test
    public void add_transaction_successfully() {
        given_add_transaction_will(success());
        controller.successMessage = "a success message";

        submitAddTransaction();

        verify(mockModel).addAttribute("message", "a success message");
    }

    @Test
    public void add_transaction_failed() {
        given_add_transaction_will(failed());
        controller.failedMessage = "a failed message";

        submitAddTransaction();

        verify(mockModel).addAttribute("message", "a failed message");
    }

    private void submitAddTransaction() {
        controller.submitAddTransaction(transaction, anyBindingResult, mockModel);
    }

    private void given_add_transaction_will(PostActions postActions) {
        when(mockTransactions.add(any(Transaction.class))).thenReturn(postActions);
    }
}