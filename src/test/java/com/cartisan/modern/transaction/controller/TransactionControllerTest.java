package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.Transactions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TransactionControllerTest {
    @Test
    public void back_page(){
        Transactions transactions = mock(Transactions.class);
        TransactionController controller = new TransactionController(transactions);

        assertEquals("add_transaction", controller.confirm(new Transaction()));
    }

    @Test
    public void add_transaction(){
        Transactions transactions = mock(Transactions.class);
        TransactionController controller = new TransactionController(transactions);

        Transaction transaction = new Transaction();
        controller.confirm(transaction);

        verify(transactions).add(transaction);
    }
}