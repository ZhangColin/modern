package com.cartisan.modern.budget;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AddBudgetOfMonthlyBudgetPlannerTest {
        MonthlyBudgetRepository mockMonthlyBudgetRepository = mock(MonthlyBudgetRepository.class);
        BudgetCategory stubBudgetCategory = mock(BudgetCategory.class);
        MonthlyBudgetPlanner planner = new MonthlyBudgetPlanner(stubBudgetCategory, mockMonthlyBudgetRepository);

        Date monthDate = parse("2016-07-01");
        MonthlyBudget monthlyBudget = new MonthlyBudget(monthDate, 100);

        Runnable afterSuccess = mock(Runnable.class);
        Runnable afterFail = mock(Runnable.class);
        Runnable whatever = () -> {};
    @Test
    public void save_monthly_budget() throws ParseException {
        planner.addMonthlyBudget(monthlyBudget, whatever, whatever);

        assertSavedMonthlyBudgetEquals(monthlyBudget);
    }

    @Test
    public void after_success_is_called_if_save_successfully() throws ParseException {
        planner.addMonthlyBudget(monthlyBudget, afterSuccess, afterFail);

        verify(afterSuccess).run();
        verify(afterFail, never()).run();
    }

    @Test
    public void after_fail_is_called_if_save_failed(){
        givenSaveWillFail();

        planner.addMonthlyBudget(monthlyBudget, afterSuccess, afterFail);

        verify(afterFail).run();
        verify(afterSuccess, never()).run();
    }

    private void givenSaveWillFail() {
        when(mockMonthlyBudgetRepository.save(any(MonthlyBudget.class))).thenThrow(IllegalArgumentException.class);
    }

    public AddBudgetOfMonthlyBudgetPlannerTest() throws ParseException {
    }

    private void assertSavedMonthlyBudgetEquals(MonthlyBudget expectedMonthlyBudget) {
        ArgumentCaptor<MonthlyBudget> captor = ArgumentCaptor.forClass(MonthlyBudget.class);
        verify(mockMonthlyBudgetRepository).save(captor.capture());
        assertEquals(expectedMonthlyBudget.getMonth(), captor.getValue().getMonth());
        assertEquals(expectedMonthlyBudget.getBudget(), captor.getValue().getBudget());
    }

    private Date parse(String source) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(source);
    }
}
