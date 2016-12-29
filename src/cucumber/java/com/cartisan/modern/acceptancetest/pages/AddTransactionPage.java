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
        UiSelect type = driver.findSelectByName("type");
        type.selectByVisibleText(transaction.getType());
        UiElement description = driver.findElementByName("description");
        description.sendKeys(transaction.getDescription());
        UiElement date = driver.findElementByName("date");
        date.sendKeys(transaction.getDate());
        UiElement amount = driver.findElementByName("amount");
        amount.sendKeys(transaction.getAmount());
    }
}
