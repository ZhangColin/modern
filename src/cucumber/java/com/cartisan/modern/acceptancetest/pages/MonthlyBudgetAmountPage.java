package com.cartisan.modern.acceptancetest.pages;

import com.cartisan.modern.acceptancetest.driver.UiDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class MonthlyBudgetAmountPage {
    @Autowired
    private UiDriver driver;

    public void open(String startDate, String endDate) {
        driver.navigateTo("http://localhost:8080/get_amount?startDate=" + startDate + "&endDate=" + endDate);
    }

    public String getAllText() {
        return driver.findElementByTag("body").getText();
    }
}
