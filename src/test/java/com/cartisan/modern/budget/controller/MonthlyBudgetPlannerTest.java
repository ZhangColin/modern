package com.cartisan.modern.budget.controller;

import com.cartisan.modern.budget.BudgetCategory;
import com.cartisan.modern.budget.MonthlyBudget;
import com.cartisan.modern.budget.MonthlyBudgetPlanner;
import com.cartisan.modern.budget.MonthlyBudgetRepository;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.codehaus.groovy.runtime.InvokerHelper.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MonthlyBudgetPlannerTest {
    BudgetCategory mockBudgetCategory = mock(BudgetCategory.class);
    MonthlyBudgetRepository stubRepository = mock(MonthlyBudgetRepository.class);
    MonthlyBudgetPlanner planner = new MonthlyBudgetPlanner(mockBudgetCategory, stubRepository);

    Date startDate = parseDate("2016-07-01");
    Date endDate = parseDate("2016-07-10");

    public MonthlyBudgetPlannerTest() throws ParseException {
    }

    @Test
    public void get_amount_from_budget_category() throws ParseException {
        given_monthly_budget_planned_as();

        planner.getAmount(startDate, endDate);

        verify(mockBudgetCategory).getAmount(startDate, endDate);
    }

    @Test
    public void read_from_repo_and_set_amount() throws ParseException {
        given_monthly_budget_planned_as(
                budget("2016-06", 30),
                budget("2016-07", 31));

        planner.getAmount(startDate, endDate);

        verify(mockBudgetCategory).setAmount(parseDate("2016-06-01"), 30);
        verify(mockBudgetCategory).setAmount(parseDate("2016-07-01"), 31);
    }

    private MonthlyBudget budget(String month, int budget) throws ParseException {
        return new MonthlyBudget(new SimpleDateFormat("yyyy-MM").parse(month), budget);
    }

    private void given_monthly_budget_planned_as(MonthlyBudget... budget) {
        when(stubRepository.findAll()).thenReturn(asList(budget));
    }

    private Date parseDate(String source) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(source);
    }
}
