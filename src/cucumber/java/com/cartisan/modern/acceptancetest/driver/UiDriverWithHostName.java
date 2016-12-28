package com.cartisan.modern.acceptancetest.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class UiDriverWithHostName implements UiDriver {
    private final String hostName = "http://localhost:8080";
    private final WebDriver webDriver = new FirefoxDriver();

    @Override
    public void close() {
        webDriver.close();
    }

    @Override
    public void navigateTo(String url) {
        webDriver.get(hostName + url);
    }

    @Override
    public UiElement findElementByName(String name) {
        return new SeleniumWebElement(webDriver.findElement(By.name(name)));
    }

    @Override
    public UiElement findElementByTag(String tag) {
        return new SeleniumWebElement(webDriver.findElement(By.tagName(tag)));
    }
}
