package com.cartisan.modern.budget.controller;

import com.cartisan.modern.budget.domain.MonthlyBudget;
import com.cartisan.modern.budget.domain.MonthlyBudgetPlanner;
import com.cartisan.modern.budget.view.PresentableAddMonthlyBudget;
import com.cartisan.modern.budget.view.PresentableMonthlyBudgetAmount;
import com.cartisan.modern.common.callback.PostActions;
import com.cartisan.modern.common.view.Message;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

import static com.cartisan.modern.common.Formats.parseDay;
import static com.cartisan.modern.common.callback.PostActionsFactory.failed;
import static com.cartisan.modern.common.callback.PostActionsFactory.success;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class MonthlyBudgetControllerTest {
    private MonthlyBudgetPlanner mockPlanner = mock(MonthlyBudgetPlanner.class);
    private Message mockMessage = mock(Message.class);
    private final PresentableAddMonthlyBudget presentableAddMonthlyBudget = new PresentableAddMonthlyBudget();
    private MonthlyBudgetController controller = new MonthlyBudgetController(mockPlanner,
            new PresentableMonthlyBudgetAmount(), presentableAddMonthlyBudget, mockMessage);
    private BindingResult stubBindingResult = mock(BindingResult.class);
    private MonthlyBudget monthlyBudget = new MonthlyBudget(parseDay("2016-07-01"), 100);

    @Before
    public void given_has_no_field_error() {
        given_has_field_error(false);
    }

    public class Add {
        @Test
        public void should_go_to_monthly_budget_add_page() {
            assertThat(controller.addMonthlyBudget()).isInstanceOf(PresentableAddMonthlyBudget.class);
        }
    }

    public class AddSubmitSuccess {

        private final MonthlyBudget monthlyBudget = new MonthlyBudget(parseDay("2016-07-01"), 100);

        @Before
        public void given_add_monthly_budget_will_success() {
            given_add_monthly_budget_will(success());
        }

        @Test
        public void should_go_to_add_monthly_budget_page() {
            assertThat(submitAddMonthlyBudget(monthlyBudget)).isInstanceOf(PresentableAddMonthlyBudget.class);
        }

        @Test
        public void should_add_monthly_budget() {
            submitAddMonthlyBudget(monthlyBudget);

            verify(mockPlanner).addMonthlyBudget(monthlyBudget);
        }

        @Test
        public void should_display_success_message_to_page() {
            controller.successMessage = "a success message";

            submitAddMonthlyBudget(monthlyBudget);

            verify(mockMessage).display("a success message");
        }

    }

    public class AddSubmitFailed {

        @Test
        public void should_display_fail_message_to_page() {
            given_add_monthly_budget_will(failed());
            controller.failedMessage = "a failed message";

            submitAddMonthlyBudget(monthlyBudget);

            verify(mockMessage).display("a failed message");
        }

    }

    public class Valid {

        private MonthlyBudget invalidMonthlyBudget = new MonthlyBudget(null, null);

        @Before
        public void given_has_some_field_error() {
            given_has_field_error(true);
        }

        @Test
        public void should_not_add_monthly_budget() {
            submitAddMonthlyBudget(invalidMonthlyBudget);

            verify(mockPlanner, never()).addMonthlyBudget(invalidMonthlyBudget);
        }

        @Test
        public void should_go_to_add_monthly_budget_page() {
            assertThat(submitAddMonthlyBudget(invalidMonthlyBudget)).isInstanceOf(PresentableAddMonthlyBudget.class);
        }

    }

    public class GetAmount {
        private final long total = 100L;
        private Date startDate = parseDay("2016-07-01");
        private Date endDate = parseDay("2016-07-10");

        private MonthlyBudgetController controller = new MonthlyBudgetController(mockPlanner,
                new PresentableMonthlyBudgetAmount() {{
                    message = "whatever message";
                }}, presentableAddMonthlyBudget, mockMessage);

        @Test
        public void should_go_to_get_amount_page() {
            assertThat(getAmount()).isInstanceOf(PresentableMonthlyBudgetAmount.class);
        }

        @Test
        public void should_get_amount_from_monthly_budget_planner() {
            getAmount();

            verify(mockPlanner).getAmount(startDate, endDate);
        }

        @Test
        public void should_pass_amount_to_page() {
            given_planner_will_return_total_as(total);
            PresentableMonthlyBudgetAmount mockPresentableMonthlyBudgetAmount = mock(PresentableMonthlyBudgetAmount.class);
            controller = new MonthlyBudgetController(mockPlanner, mockPresentableMonthlyBudgetAmount, presentableAddMonthlyBudget, mockMessage);

            getAmount();

            verify(mockPresentableMonthlyBudgetAmount).display(100L);
        }

        private void given_planner_will_return_total_as(long total) {
            when(mockPlanner.getAmount(startDate, endDate)).thenReturn(total);
        }

        private ModelAndView getAmount() {
            return controller.totalAmountOfMonthlyBudget(startDate, endDate);
        }

    }

    private ModelAndView submitAddMonthlyBudget(MonthlyBudget monthlyBudget) {
        return controller.submitAddMonthlyBudget(monthlyBudget, stubBindingResult);
    }

    private void given_add_monthly_budget_will(PostActions postActions) {
        when(mockPlanner.addMonthlyBudget(any(MonthlyBudget.class))).thenReturn(postActions);
    }

    private void given_has_field_error(boolean value) {
        when(stubBindingResult.hasFieldErrors()).thenReturn(value);
    }

}
