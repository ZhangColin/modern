package com.cartisan.modern.acceptancetest.pages;

import com.cartisan.modern.acceptancetest.driver.UiDriver;
import com.cartisan.modern.acceptancetest.driver.UiElement;
import com.cartisan.modern.acceptancetest.driver.UiSelect;
import com.cartisan.modern.acceptancetest.steps.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class AddTransactionPage {
    @Autowired
    private UiDriver driver;

    public void add(Transaction transaction){
        driver.navigateTo("/add_transaction");
        setType(transaction.getType());
        setDescription(transaction.getDescription());
        setDate(transaction.getDate());
        setAmountAndSubmit(transaction.getAmount());
    }

    private void setAmountAndSubmit(String amount) {
        UiElement element = driver.findElementByName("amount");
        element.sendKeys(amount);
        element.submit();
    }

    private void setDate(String date) {
        driver.findElementByName("date").sendKeys(date);
    }

    private void setDescription(String description) {
        driver.findElementByName("description").sendKeys(description);
    }

    private void setType(String type) {
        driver.findSelectByName("type").selectByVisibleText(type);
    }
}
