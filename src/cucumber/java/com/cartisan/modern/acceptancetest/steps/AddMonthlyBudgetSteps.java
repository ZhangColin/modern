package com.cartisan.modern.acceptancetest.steps;


import com.cartisan.modern.acceptancetest.pages.AddMonthlyBudgetPage;
import com.cartisan.modern.budget.MonthlyBudget;
import com.cartisan.modern.budget.MonthlyBudgetRepository;
import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static com.cartisan.modern.acceptancetest.steps.Formats.MONTH;
import static org.junit.Assert.assertEquals;

public class AddMonthlyBudgetSteps {
    @Autowired
    private AddMonthlyBudgetPage page;

    @Autowired
    private MonthlyBudgetRepository monthlyBudgetRepository;

    @When("^add budget for \"([^\"]*)\" with amount (\\d+)$")
    public void add_budget_for_with_amount(String month, Integer budget) {
        page.addMonthlyBudget(month, budget);
    }

    @Then("^monthly budget (\\d+) for \"([^\"]*)\" is saved$")
    public void monthly_budget_for_is_saved(Integer budget, @Format(MONTH)Date month) {
        assertEquals(1, monthlyBudgetRepository.count());

        monthlyBudgetRepository.findAll().forEach(monthlyBudget -> {
            assertEquals(month, monthlyBudget.getMonth());
            assertEquals(budget, monthlyBudget.getBudget());
        });
    }

    @Given("^budget (\\d+) has been set for month \"([^\"]*)\"$")
    public void budget_has_been_set_for_month(Integer budget, @Format(MONTH)Date month) {
        monthlyBudgetRepository.save(new MonthlyBudget(month, budget));
    }

    @When("^add budget for \"([^\"]*)\" with a new amount (\\d+)$")
    public void add_budget_for_with_a_new_amount(String month, Integer budget) {
        add_budget_for_with_amount(month, budget);
    }

    @Then("^the budget for \"([^\"]*)\" is (\\d+)$")
    public void the_budget_for_with_a_new_amount(@Format(MONTH)Date month, Integer budget) {
        monthly_budget_for_is_saved(budget, month);
    }

}
