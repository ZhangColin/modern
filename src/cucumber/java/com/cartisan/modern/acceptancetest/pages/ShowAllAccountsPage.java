package com.cartisan.modern.acceptancetest.pages;

import com.cartisan.modern.acceptancetest.driver.UiDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.cartisan.modern.common.controller.Urls.ACCOUNTS;
import static com.cartisan.modern.common.view.MessageSources.LABEL_TEXT_FULL_NAME;

@Component
@Scope("cucumber-glue")
@PropertySource(LABEL_TEXT_FULL_NAME)
public class ShowAllAccountsPage {
    @Autowired
    UiDriver driver;

    @Value("${accounts.index.label.title}")
    String title;

    public void show(){
        driver.navigateTo(ACCOUNTS);
        driver.waitForTestPresent(title);
    }
}
