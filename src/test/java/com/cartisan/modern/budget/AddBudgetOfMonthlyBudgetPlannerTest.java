package com.cartisan.modern.budget;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddBudgetOfMonthlyBudgetPlannerTest {
    @Test
    public void save_monthly_budget() throws ParseException {
        MonthlyBudgetRepository mockMonthlyBudgetRepository = mock(MonthlyBudgetRepository.class);
        BudgetCategory stubBudgetCategory = mock(BudgetCategory.class);
        MonthlyBudgetPlanner planner = new MonthlyBudgetPlanner(stubBudgetCategory, mockMonthlyBudgetRepository);

        Date monthDate = parse("2016-07-01");
        planner.addMonthlyBudget(new MonthlyBudget(monthDate, 100), ()->{});

        ArgumentCaptor<MonthlyBudget> captor = ArgumentCaptor.forClass(MonthlyBudget.class);
        verify(mockMonthlyBudgetRepository).save(captor.capture());
        assertEquals(monthDate, captor.getValue().getMonth());
        assertEquals(100, captor.getValue().getBudget().intValue());
    }

    private Date parse(String source) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(source);
    }
}
