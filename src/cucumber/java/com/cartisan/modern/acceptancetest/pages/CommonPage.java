package com.cartisan.modern.acceptancetest.pages;

import com.cartisan.modern.acceptancetest.driver.UiDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonPage {
    @Autowired
    protected UiDriver driver;

    public String getAllText() {
        return driver.findElementByTag("body").getText();
    }
}
