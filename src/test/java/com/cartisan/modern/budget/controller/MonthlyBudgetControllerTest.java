package com.cartisan.modern.budget.controller;

import com.cartisan.modern.budget.domain.MonthlyBudget;
import com.cartisan.modern.budget.domain.MonthlyBudgetPlanner;
import com.cartisan.modern.budget.view.PresentableAddMonthlyBudget;
import com.cartisan.modern.budget.view.PresentableMonthlyBudgetAmount;
import com.cartisan.modern.common.callback.PostActions;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Date;

import static com.cartisan.modern.common.Formats.parseDay;
import static com.cartisan.modern.common.callback.PostActionsFactory.failed;
import static com.cartisan.modern.common.callback.PostActionsFactory.success;
import static com.cartisan.modern.common.controller.Urls.MONTHLYBUDGET_ADD;
import static com.cartisan.modern.common.controller.Urls.MONTHLYBUDGET_TOTALAMOUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class MonthlyBudgetControllerTest {
    private MonthlyBudgetPlanner mockPlanner = mock(MonthlyBudgetPlanner.class);
    private PresentableMonthlyBudgetAmount mockPresentableMonthlyBudgetAmount = mock(PresentableMonthlyBudgetAmount.class);
    private PresentableAddMonthlyBudget mockPresentableAddMonthlyBudget = mock(PresentableAddMonthlyBudget.class);
    private MonthlyBudgetController controller = new MonthlyBudgetController(mockPlanner, mockPresentableMonthlyBudgetAmount, mockPresentableAddMonthlyBudget);
    private Model mockModel = mock(Model.class);
    private BindingResult stubBindingResult = mock(BindingResult.class);

    @Before
    public void given_has_no_field_error(){
        given_has_field_error(false);
    }

    public class Add{
        @Test
        public void should_go_to_monthly_budget_add_page() {
            assertThat(addMonthlyBudget()).isEqualTo(MONTHLYBUDGET_ADD);
        }

        @Test
        public void should_display_add_monthly_budget_view() {
            addMonthlyBudget();

            verify(mockPresentableAddMonthlyBudget).display(mockModel);
        }

        private String addMonthlyBudget() {
            return controller.addMonthlyBudget(mockModel);
        }
    }

    public class AddSubmitSuccess{
        private final MonthlyBudget monthlyBudget = new MonthlyBudget(parseDay("2016-07-01"), 100);

        @Before
        public void given_add_monthly_budget_will_success(){
            given_add_monthly_budget_will(success());
        }

        @Test
        public void should_go_to_add_monthly_budget_page() {
            assertThat(submitAddMonthlyBudget(monthlyBudget)).isEqualTo(MONTHLYBUDGET_ADD);
        }

        @Test
        public void should_display_add_monthly_budget_view(){
            submitAddMonthlyBudget(monthlyBudget);

            verify(mockPlanner).addMonthlyBudget(monthlyBudget);
        }

        @Test
        public void should_add_monthly_budget(){
            submitAddMonthlyBudget(monthlyBudget);

            verify(mockPlanner).addMonthlyBudget(monthlyBudget);
        }

        @Test
        public void should_return_add_success_message_to_page() {
            controller.successMessage = "a success message";

            submitAddMonthlyBudget(monthlyBudget);

            verify(mockModel).addAttribute("message", "a success message");
        }
    }

    public class AddSubmitFailed{
        private final MonthlyBudget monthlyBudget = new MonthlyBudget(parseDay("2016-07-01"), 100);

        @Before
        public void given_add_monthly_budget_will_failed(){
            given_add_monthly_budget_will(failed());
        }

        @Test
        public void should_go_to_add_monthly_budget_page() {
            new AddSubmitSuccess().should_go_to_add_monthly_budget_page();
        }

        @Test
        public void should_add_monthly_budget(){
            new AddSubmitSuccess().should_add_monthly_budget();
        }

        @Test
        public void should_display_add_monthly_budget_view(){
            new AddSubmitSuccess().should_display_add_monthly_budget_view();
        }

        @Test
        public void return_add_fail_message_to_page_when_add_budget_for_month_failed(){
            controller.failedMessage = "a failed message";

            submitAddMonthlyBudget(monthlyBudget);

            verify(mockModel).addAttribute("message", "a failed message");
        }
    }

    public class Valid{
        private MonthlyBudget invalidMonthlyBudget = new MonthlyBudget(null, null);

        @Before
        public void given_has_some_field_error(){
            given_has_field_error(true);
        }

        @Test
        public void will_not_add_monthly_budget_when_has_field_error() {
            submitAddMonthlyBudget(invalidMonthlyBudget);

            verify(mockPlanner, never()).addMonthlyBudget(invalidMonthlyBudget);
        }

        @Test
        public void will_go_to_add_monthly_budget_page_when_has_field_error() {
            assertThat(submitAddMonthlyBudget(invalidMonthlyBudget)).isEqualTo(MONTHLYBUDGET_ADD);
        }

    }

    public class GetAmount{
        private Date startDate = parseDay("2016-07-01");
        private Date endDate = parseDay("2016-07-10");

        @Test
        public void go_to_get_amount_page(){
            assertThat(getAmount()).isEqualTo(MONTHLYBUDGET_TOTALAMOUNT);
        }

        @Test
        public void get_amount_from_monthly_budget_planner() {
            getAmount();

            verify(mockPlanner).getAmount(startDate, endDate);
        }

        @Test
        public void pass_amount_to_page() {
            when(mockPlanner.getAmount(startDate, endDate)).thenReturn(100L);

            getAmount();

            verify(mockPresentableMonthlyBudgetAmount).display(mockModel, 100L);
        }

        private String getAmount() {
            return controller.totalAmountOfMonthlyBudget(startDate, endDate, mockModel);
        }
    }

    private String submitAddMonthlyBudget(MonthlyBudget monthlyBudget) {
        return controller.submitAddMonthlyBudget(monthlyBudget, stubBindingResult, mockModel);
    }

    private void given_add_monthly_budget_will(PostActions postActions) {
        when(mockPlanner.addMonthlyBudget(any(MonthlyBudget.class))).thenReturn(postActions);
    }

    private void given_has_field_error(boolean value) {
        when(stubBindingResult.hasFieldErrors()).thenReturn(value);
    }
}
