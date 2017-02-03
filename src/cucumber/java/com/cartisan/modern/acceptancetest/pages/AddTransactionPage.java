package com.cartisan.modern.acceptancetest.pages;

import com.cartisan.modern.acceptancetest.data.transaction.EditableTransaction;
import com.cartisan.modern.acceptancetest.driver.UiDriver;
import com.cartisan.modern.acceptancetest.driver.UiElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.cartisan.modern.common.controller.Urls.TRANSACTIONS_ADD;

@Component
@Scope("cucumber-glue")
public class AddTransactionPage {
    @Autowired
    private UiDriver driver;

    public void add(EditableTransaction editableTransaction) {
        driver.navigateTo(TRANSACTIONS_ADD);
        setType(editableTransaction.getType());
        setDescription(editableTransaction.getDescription());
        setDate(editableTransaction.getDate());
        setAmountAndSubmit(editableTransaction.getAmount());
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
