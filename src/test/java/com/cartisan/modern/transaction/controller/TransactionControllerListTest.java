package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.transaction.domain.Transactions;
import org.junit.Test;
import org.springframework.ui.Model;

import static com.cartisan.modern.common.controller.Urls.TRANSACTION_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class TransactionControllerListTest {
    private Transactions mockTransactions = mock(Transactions.class);
    private TransactionController controller = new TransactionController(mockTransactions);
    private Model mockModel = mock(Model.class);

    @Test
    public void go_to_transaction_list_page(){
        assertThat(controller.showTransactions(mockModel)).isEqualTo(TRANSACTION_LIST);
    }
}
