package com.cartisan.modern.acceptancetest.pages;

import com.cartisan.modern.acceptancetest.driver.UiDriver;
import com.cartisan.modern.acceptancetest.driver.UiElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class LoginPage {
    @Autowired
    private UiDriver driver;

    public void login(String userName, String password) {
        driver.navigateTo("http://localhost:8080/login");
        UiElement userNameTextBox = driver.findElementByName("username");
        userNameTextBox.sendKeys(userName);
        UiElement passwordBox = driver.findElementByName("password");
        passwordBox.sendKeys(password);
        userNameTextBox.submit();
    }

    public String getAllText() {
        return driver.findElementByTag("body").getText();
    }
}
