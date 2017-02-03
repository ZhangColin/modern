package com.cartisan.modern.transaction.view;

import org.junit.Test;

import static com.cartisan.modern.common.controller.Urls.TRANSACTIONS_ADD;
import static com.cartisan.modern.transaction.domain.Transaction.Type.values;
import static org.assertj.core.api.Assertions.assertThat;

public class PresentableAddTransactionTest {
    PresentableAddTransaction presentableAddTransaction = new PresentableAddTransaction();

    @Test
    public void should_pass_type_values_to_page() {
        assertThat(presentableAddTransaction.getModel().get("types")).isEqualTo(values());
    }

    @Test
    public void should_go_to_transaction_add_page() {
        assertThat(presentableAddTransaction.getViewName()).isEqualTo(TRANSACTIONS_ADD);
    }
}
