package com.cartisan.modern.acceptancetest.pages;

import com.cartisan.modern.acceptancetest.driver.UiDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.cartisan.modern.common.controller.Urls.TRANSACTION_LIST;

@Component
@Scope("cucumber-glue")
public class ShowAllTransactionsPage {
    @Autowired
    private UiDriver uiDriver;

    public void show(){
        uiDriver.navigateTo(TRANSACTION_LIST);
    }
}
