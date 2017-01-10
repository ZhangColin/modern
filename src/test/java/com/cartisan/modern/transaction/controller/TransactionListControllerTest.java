package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.transaction.domain.Transactions;
import org.junit.Test;

import static com.cartisan.modern.Urls.TRANSACTION_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class TransactionListControllerTest {
    private Transactions mockTransactions = mock(Transactions.class);
    private TransactionController controller = new TransactionController(mockTransactions);

    @Test
    public void go_to_transaction_list_page(){
        assertThat(controller.showTransactions()).isEqualTo(TRANSACTION_LIST);
    }
}
