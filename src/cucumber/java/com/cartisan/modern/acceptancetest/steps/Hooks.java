package com.cartisan.modern.acceptancetest.steps;

import com.cartisan.modern.acceptancetest.driver.UiDriver;
import cucumber.api.java.After;
import org.springframework.beans.factory.annotation.Autowired;

public class Hooks {
    @Autowired
    private UiDriver uiDriver;

    @After
    public void closeUiDriver(){
        uiDriver.close();
    }
}
