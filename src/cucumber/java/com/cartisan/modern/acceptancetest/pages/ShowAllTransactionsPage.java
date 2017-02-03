package com.cartisan.modern.acceptancetest.pages;

import com.cartisan.modern.acceptancetest.driver.UiDriver;
import com.cartisan.modern.common.view.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.cartisan.modern.common.controller.Urls.TRANSACTION;

@Component
@Scope("cucumber-glue")
public class ShowAllTransactionsPage {
    @Autowired
    private UiDriver uiDriver;

    public void show() {
        uiDriver.navigateTo(TRANSACTION);
    }

    public void navigateToPage(int pageNumber) {
        uiDriver.navigateToWithParams(TRANSACTION, paramsWithPage(pageNumber));
    }

    private Params paramsWithPage(int pageNumber) {
        Params params = new Params();
        params.add("page", String.valueOf(pageNumber));
        return params;
    }
}
