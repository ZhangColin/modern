package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.Transactions;
import org.junit.Test;
import org.springframework.ui.Model;

import static com.cartisan.modern.transaction.RunnableHelper.createRunnableArgumentInvoker;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TransactionControllerTest {
    private static final int SUCCESS = 1;
    private static final int FAILED = 2;
    Transactions transactions = mock(Transactions.class);
    TransactionController controller = new TransactionController(transactions);
    Transaction transaction = new Transaction();
    Model mockModel = mock(Model.class);

    @Test
    public void back_page() {
        assertEquals("add_transaction", controller.addTransaction(transaction, mockModel));
    }

    @Test
    public void add_transaction() {
        controller.addTransaction(transaction, mockModel);

        verify(transactions).add(eq(transaction), any(Runnable.class), any(Runnable.class));
    }

    @Test
    public void add_transaction_successfully(){
        given_add_transaction_will(SUCCESS);

        controller.addTransaction(transaction, mockModel);

        verify(mockModel).addAttribute("message", "Successfully add transaction");
    }

    @Test
    public void add_transaction_failed(){
        given_add_transaction_will(FAILED);

        controller.addTransaction(transaction, mockModel);

        verify(mockModel).addAttribute("message", "Add transaction failed");
    }

    private void given_add_transaction_will(int i) {
        doAnswer(createRunnableArgumentInvoker(i)).when(transactions).add(any(Transaction.class), any(Runnable.class), any(Runnable.class));
    }
}