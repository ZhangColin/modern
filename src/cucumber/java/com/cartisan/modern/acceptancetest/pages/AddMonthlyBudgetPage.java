package com.cartisan.modern.acceptancetest.pages;

import com.cartisan.modern.acceptancetest.driver.UiElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class AddMonthlyBudgetPage extends CommonPage {
    public void addMonthlyBudget(String month, Integer budget) {
        driver.navigateTo("/add_budget_for_month");
        UiElement monthTextBox = driver.findElementByName("month");
        monthTextBox.sendKeys(month);
        UiElement budgetTextBox = driver.findElementByName("budget");
        budgetTextBox.sendKeys(String.valueOf(budget));
        budgetTextBox.submit();
    }

}
