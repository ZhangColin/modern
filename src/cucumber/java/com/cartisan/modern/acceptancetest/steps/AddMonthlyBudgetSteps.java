package com.cartisan.modern.acceptancetest.steps;


import com.cartisan.modern.budget.MonthlyBudget;
import com.cartisan.modern.budget.MonthlyBudgetRepository;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AddMonthlyBudgetSteps implements En {
    private WebDriver driver;

    @Autowired
    private MonthlyBudgetRepository monthlyBudgetRepository;

    {
        When("^add budget for \"([^\"]*)\" with amount (\\d+)$", (String month, Integer budget) -> {
            addBudgetForMonth(month, budget);
        });

        Then("^monthly budget (\\d+) for \"([^\"]*)\" is saved$", (Integer budget, String month) -> {
            verifyMonthlyBudgetIsSaved(budget, month);
        });

        Given("^budget (\\d+) has been set for month \"([^\"]*)\"$", (Integer budget, String month) -> {
            Date monthDate = null;
            try {
                monthDate = new SimpleDateFormat("yyyy-MM").parse(month);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            monthlyBudgetRepository.save(new MonthlyBudget(monthDate, budget));
        });

        When("^add budget for \"([^\"]*)\" with a new amount (\\d+)$", (String month, Integer budget) -> {
            addBudgetForMonth(month, budget);
        });

        Then("^the budget for \"([^\"]*)\" is (\\d+)$", (String month, Integer budget) -> {
            verifyMonthlyBudgetIsSaved(budget, month);
        });
    }

    private void verifyMonthlyBudgetIsSaved(Integer budget, String month) {
        assertEquals(1, monthlyBudgetRepository.count());

        monthlyBudgetRepository.findAll().forEach(monthlyBudget -> {
            Date monthDate = null;
            try {
                monthDate = new SimpleDateFormat("yyyy-MM").parse(month);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            assertEquals(monthDate, monthlyBudget.getMonth());
            assertEquals(budget, monthlyBudget.getBudget());
        });

        monthlyBudgetRepository.deleteAll();
        driver.close();
    }

    private void addBudgetForMonth(String month, Integer budget) {
        driver = new FirefoxDriver();
        driver.get("http://localhost:8080/add_budget_for_month");
        WebElement monthTextBox = driver.findElement(By.name("month"));
        monthTextBox.sendKeys(month);
        WebElement budgetTextBox = driver.findElement(By.name("budget"));
        budgetTextBox.sendKeys(String.valueOf(budget));
        budgetTextBox.submit();
    }
}
