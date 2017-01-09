package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.transaction.domain.Transactions;
import org.junit.Test;

import static com.cartisan.modern.Urls.TRANSACTION_LIST;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class TransactionListControllerTest {
    private Transactions mockTransactions = mock(Transactions.class);
    private TransactionController controller = new TransactionController(mockTransactions);

    @Test
    public void go_to_transaction_list_page(){
        assertEquals(TRANSACTION_LIST, controller.showTransactions());
    }
}
