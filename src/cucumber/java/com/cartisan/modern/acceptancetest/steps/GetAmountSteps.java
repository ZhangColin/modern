package com.cartisan.modern.acceptancetest.steps;


import com.cartisan.modern.acceptancetest.driver.UiDriver;
import com.cartisan.modern.acceptancetest.pages.CommonPage;
import com.cartisan.modern.acceptancetest.pages.MonthlyBudgetAmountPage;
import com.cartisan.modern.budget.MonthlyBudget;
import com.cartisan.modern.budget.MonthlyBudgetRepository;
import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.cartisan.modern.acceptancetest.steps.Formats.MONTH;
import static org.junit.Assert.assertTrue;

public class GetAmountSteps{
    @Autowired
    private MonthlyBudgetAmountPage page;

    @Autowired
    private CommonPage commonPage;

    @Autowired
    MonthlyBudgetRepository monthlyBudgetRepository;


    @Given("^budget planned for \"([^\"]*)\" is (\\d+)$")
    public void budget_planned_for_is(@Format(MONTH)Date month, Integer budget) {
        monthlyBudgetRepository.save(new MonthlyBudget(month, budget));
    }

    @When("^get amount of period from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void get_amount_of_period_from_to(String startDate, String endDate) {
        page.open(startDate, endDate);
    }

    @Then("^the amount is (\\d+)$")
    public void the_amount_is(Integer amount) {
        assertTrue(commonPage.getAllText().contains(String.valueOf(amount)));
    }

}
