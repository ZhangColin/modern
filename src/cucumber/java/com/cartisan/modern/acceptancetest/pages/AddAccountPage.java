package com.cartisan.modern.acceptancetest.pages;

import com.cartisan.modern.acceptancetest.data.account.EditableAccount;
import com.cartisan.modern.acceptancetest.driver.UiDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.cartisan.modern.common.controller.Urls.ACCOUNT_ADD;

@Component
@Scope("cucumber-glue")
public class AddAccountPage {
    @Autowired
    UiDriver driver;

    public void add(EditableAccount account) {
        driver.navigateTo(ACCOUNT_ADD);
        setName(account.getName());
        setBalanceBroughtForward(account.getBalanceBroughtForward());
    }

    private void setBalanceBroughtForward(String balanceBroughtForward) {
        driver.findElementByName("balanceBroughtForward").sendKeys(balanceBroughtForward);
    }

    private void setName(String name) {
        driver.findElementByName("name").sendKeys(name);
    }
}
