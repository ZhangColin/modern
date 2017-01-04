package com.cartisan.modern.budget.controller;

import com.cartisan.modern.budget.domain.MonthlyBudget;
import com.cartisan.modern.budget.domain.MonthlyBudgetPlanner;
import org.junit.Test;
import org.springframework.ui.Model;

import java.text.ParseException;

import static com.cartisan.modern.common.Formats.parseDay;
import static com.cartisan.modern.transaction.RunnableHelper.createRunnableArgumentInvoker;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class AddBudgetForMonthControllerTest {
    private static final int SUCCESS = 1;
    private static final int FAIL = 2;
    MonthlyBudgetPlanner mockPlanner = mock(MonthlyBudgetPlanner.class);
    MonthlyBudgetController controller = new MonthlyBudgetController(mockPlanner);
    Model mockModel = mock(Model.class);
    private final MonthlyBudget monthlyBudget = new MonthlyBudget(parseDay("2016-07-01"), 100);

    public AddBudgetForMonthControllerTest() throws ParseException {
    }

    @Test
    public void go_to_add_budget_for_month_page() {
        assertEquals("add_budget_for_month", controller.confirm(monthlyBudget, mockModel));
    }

    @Test
    public void add_monthly_budget(){
        controller.confirm(monthlyBudget, mockModel);

        verify(mockPlanner).addMonthlyBudget(eq(monthlyBudget), any(Runnable.class), any(Runnable.class));
    }

    @Test
    public void return_add_success_message_to_page_when_add_budget_for_month_successfully() {
        given_add_monthly_budget_will(SUCCESS);

        controller.confirm(monthlyBudget, mockModel);

        verify(mockModel).addAttribute("message", "Successfully add budget for month");
    }

    @Test
    public void return_add_fail_message_to_page_when_add_budget_for_for_month_failed(){
        given_add_monthly_budget_will(FAIL);

        controller.confirm(monthlyBudget, mockModel);

        verify(mockModel).addAttribute("message", "Add budget for month failed");
    }

    private void given_add_monthly_budget_will(int i) {
        doAnswer(createRunnableArgumentInvoker(i)).when(mockPlanner).addMonthlyBudget(any(MonthlyBudget.class), any(Runnable.class), any(Runnable.class));
    }
}
