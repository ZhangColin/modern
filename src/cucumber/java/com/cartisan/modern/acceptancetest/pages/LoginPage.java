package com.cartisan.modern.acceptancetest.pages;

import com.cartisan.modern.acceptancetest.driver.UiElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class LoginPage extends CommonPage {
    public void login(String userName, String password) {
        driver.navigateTo("/login");
        UiElement userNameTextBox = driver.findElementByName("username");
        userNameTextBox.sendKeys(userName);
        UiElement passwordBox = driver.findElementByName("password");
        passwordBox.sendKeys(password);
        userNameTextBox.submit();
    }
}
