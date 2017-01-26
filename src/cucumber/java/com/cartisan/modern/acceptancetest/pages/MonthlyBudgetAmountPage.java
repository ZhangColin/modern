package com.cartisan.modern.acceptancetest.pages;

import com.cartisan.modern.acceptancetest.driver.UiDriver;
import com.cartisan.modern.common.view.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.cartisan.modern.common.controller.Urls.MONTHLYBUDGET_TOTALAMOUNT;

@Component
@Scope("cucumber-glue")
public class MonthlyBudgetAmountPage {
    @Autowired
    private UiDriver driver;

    public void open(String startDate, String endDate) {
        Params params = new Params();
        params.add("startDate", startDate);
        params.add("endDate", endDate);
        driver.navigateToWithParams(MONTHLYBUDGET_TOTALAMOUNT, params);
    }

}
