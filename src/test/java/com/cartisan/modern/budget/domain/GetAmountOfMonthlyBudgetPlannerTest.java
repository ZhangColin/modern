package com.cartisan.modern.budget.domain;

import com.cartisan.modern.budget.repository.MonthlyBudgetRepository;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.cartisan.modern.common.Formats.DAY;
import static com.cartisan.modern.common.Formats.MONTH;
import static com.cartisan.modern.common.Formats.parseDay;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class GetAmountOfMonthlyBudgetPlannerTest {
    BudgetCategory mockBudgetCategory = mock(BudgetCategory.class);
    MonthlyBudgetRepository stubRepository = mock(MonthlyBudgetRepository.class);
    MonthlyBudgetPlanner planner = new MonthlyBudgetPlanner(mockBudgetCategory, stubRepository);

    Date startDate = parseDay("2016-07-01");
    Date endDate = parseDay("2016-07-10");

    public GetAmountOfMonthlyBudgetPlannerTest() throws ParseException {
    }

    @Test
    public void get_amount_from_budget_category() throws ParseException {
        given_monthly_budget_planned_as();
        given_total_amount_is(100L);

        assertEquals(100L, planner.getAmount(startDate, endDate));
    }

    @Test
    public void read_from_repo_and_set_amount() throws ParseException {
        given_monthly_budget_planned_as(
                budget("2016-06", 30),
                budget("2016-07", 31));

        planner.getAmount(startDate, endDate);

        verify(mockBudgetCategory).setAmount(parseDay("2016-06-01"), 30);
        verify(mockBudgetCategory).setAmount(parseDay("2016-07-01"), 31);
    }

    private MonthlyBudget budget(String month, int budget) throws ParseException {
        return new MonthlyBudget(new SimpleDateFormat(MONTH).parse(month), budget);
    }

    private void given_monthly_budget_planned_as(MonthlyBudget... budget) {
        when(stubRepository.findAll()).thenReturn(asList(budget));
    }

    private void given_total_amount_is(long total) {
        when(mockBudgetCategory.getAmount(any(Date.class), any(Date.class))).thenReturn(total);
    }
}
