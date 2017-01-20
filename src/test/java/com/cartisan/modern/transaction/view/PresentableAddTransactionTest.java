package com.cartisan.modern.transaction.view;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import static com.cartisan.modern.transaction.domain.Transaction.Type.values;

public class PresentableAddTransactionTest {
    @Test
    public void should_pass_type_values_to_page(){
        HttpServletRequest mockHttpServletRequest = mock(HttpServletRequest.class);
        PresentableAddTransaction presentableAddTransaction = new PresentableAddTransaction(mockHttpServletRequest);

        presentableAddTransaction.display();

        verify(mockHttpServletRequest).setAttribute("types", values());
    }
}
