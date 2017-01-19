package com.cartisan.modern.budget.view;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PresentableMonthlyBudgetAmountTest {
    @Test
    public void should_pass_amount_message_to_page() {
        HttpServletRequest mockHttpServletRequest = mock(HttpServletRequest.class);
        PresentableMonthlyBudgetAmount presentableMonthlyBudgetAmount = new PresentableMonthlyBudgetAmount(mockHttpServletRequest);
        presentableMonthlyBudgetAmount.message = "Amount is %s";

        presentableMonthlyBudgetAmount.display(100);

        verify(mockHttpServletRequest).setAttribute("amount", "Amount is 100");
    }
}
