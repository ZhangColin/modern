package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.common.PostActions;
import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.Transactions;
import org.junit.Test;
import org.springframework.ui.Model;

import static com.cartisan.modern.common.PostActionsFactory.failed;
import static com.cartisan.modern.common.PostActionsFactory.success;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class TransactionControllerTest {
    private static final int SUCCESS = 1;
    private static final int FAILED = 2;
    Transactions mockTransactions = mock(Transactions.class);
    TransactionController controller = new TransactionController(mockTransactions);
    Transaction transaction = new Transaction();
    Model mockModel = mock(Model.class);

    @Test
    public void back_page() {
        given_add_transaction_will(success());
        assertEquals("add_transaction", controller.addTransaction(transaction, mockModel));
    }

    @Test
    public void add_transaction() {
        given_add_transaction_will(success());

        controller.addTransaction(transaction, mockModel);

        verify(mockTransactions).add(eq(transaction));
    }

    @Test
    public void add_transaction_successfully() {
        given_add_transaction_will(success());

        controller.addTransaction(transaction, mockModel);

        verify(mockModel).addAttribute("message", "Successfully add transaction");
    }

    @Test
    public void add_transaction_failed() {
        given_add_transaction_will(failed());

        controller.addTransaction(transaction, mockModel);

        verify(mockModel).addAttribute("message", "Add transaction failed");
    }

    private void given_add_transaction_will(PostActions postActions) {
        when(mockTransactions.add(any(Transaction.class))).thenReturn(postActions);
    }
}