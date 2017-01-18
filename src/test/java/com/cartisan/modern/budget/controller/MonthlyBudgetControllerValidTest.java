package com.cartisan.modern.budget.controller;

import com.cartisan.modern.budget.domain.MonthlyBudget;
import com.cartisan.modern.budget.domain.MonthlyBudgetPlanner;
import com.cartisan.modern.budget.view.MonthlyBudgetAmount;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static com.cartisan.modern.common.controller.Urls.MONTHLYBUDGET_ADD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MonthlyBudgetControllerValidTest {
    private MonthlyBudgetPlanner mockPlanner = mock(MonthlyBudgetPlanner.class);
    private MonthlyBudgetAmount stubMonthlyBudgetAmount = mock(MonthlyBudgetAmount.class);
    private MonthlyBudgetController controller = new MonthlyBudgetController(mockPlanner, stubMonthlyBudgetAmount);
    private Model notUsedModel = mock(Model.class);
    private MonthlyBudget invalidMonthlyBudget = new MonthlyBudget(null, null);
    private BindingResult stubBindingResult = mock(BindingResult.class);

    @Before
    public void givenHasFieldErrors(){
        when(stubBindingResult.hasFieldErrors()).thenReturn(true);
    }

    @Test
    public void will_not_add_monthly_budget_when_has_field_error() {
        submitAddMonthlyBudget();

        verify(mockPlanner, never()).addMonthlyBudget(invalidMonthlyBudget);
    }

    @Test
    public void will_go_to_add_monthly_budget_page_when_has_field_error() {
        assertThat(submitAddMonthlyBudget()).isEqualTo(MONTHLYBUDGET_ADD);
    }

    private String submitAddMonthlyBudget() {
        return controller.submitAddMonthlyBudget(invalidMonthlyBudget, stubBindingResult, notUsedModel);
    }
}
