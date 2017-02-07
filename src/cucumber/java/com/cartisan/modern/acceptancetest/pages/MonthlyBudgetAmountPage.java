package com.cartisan.modern.acceptancetest.pages;

import com.cartisan.modern.acceptancetest.driver.UiDriver;
import com.cartisan.modern.common.view.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.cartisan.modern.common.controller.Urls.MONTHLYBUDGETS_TOTALAMOUNT;
import static com.cartisan.modern.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;
import static java.lang.String.format;

@Component
@Scope("cucumber-glue")
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class MonthlyBudgetAmountPage {
    @Autowired
    private UiDriver driver;

    @Value("${monthlybudgets.totalamount.amount}")
    String totalAmountMessage;

    public void open(String startDate, String endDate) {
        Params params = new Params();
        params.add("startDate", startDate);
        params.add("endDate", endDate);
        driver.navigateToWithParams(MONTHLYBUDGETS_TOTALAMOUNT, params);
        driver.waitForTestPresent(format(totalAmountMessage, ""));
    }

}
