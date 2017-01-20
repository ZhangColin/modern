package com.cartisan.modern.transaction.view;

import com.cartisan.modern.common.view.Model;
import org.junit.Test;

import static com.cartisan.modern.transaction.domain.Transaction.Type.values;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PresentableAddTransactionTest {
    @Test
    public void should_pass_type_values_to_page(){
        Model mockModel = mock(Model.class);
        PresentableAddTransaction presentableAddTransaction = new PresentableAddTransaction(mockModel);

        presentableAddTransaction.display();

        verify(mockModel).addAttribute("types", values());
    }
}
