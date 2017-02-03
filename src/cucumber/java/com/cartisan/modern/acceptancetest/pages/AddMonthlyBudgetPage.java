package com.cartisan.modern.acceptancetest.pages;

import com.cartisan.modern.acceptancetest.driver.UiDriver;
import com.cartisan.modern.acceptancetest.driver.UiElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.cartisan.modern.common.controller.Urls.MONTHLYBUDGETS_ADD;

@Component
@Scope("cucumber-glue")
public class AddMonthlyBudgetPage {
    @Autowired
    private UiDriver driver;

    public void addMonthlyBudget(String month, String budget) {
        driver.navigateTo(MONTHLYBUDGETS_ADD);
        setMonth(month);
        setBudgetAndSubmit(budget);
    }

    private void setBudgetAndSubmit(String budget) {
        UiElement budgetTextBox = driver.findElementByName("budget");
        budgetTextBox.sendKeys(budget);
        budgetTextBox.submit();
    }

    private void setMonth(String month) {
        driver.findElementByName("month").sendKeys(month);
    }

}
