package com.cartisan.modern.acceptancetest.driver;

import com.cartisan.modern.common.view.Params;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class UiDriverWithHostName implements UiDriver {
    private final String hostName = "http://localhost:8080";
    private final UiDriver originalDriver = new SeleniumWebDriver();

    @Override
    public void close() {
        originalDriver.close();
    }

    @Override
    public void navigateTo(String url) {
        originalDriver.navigateTo(hostName + url);
    }

    @Override
    public UiElement findElementByName(String name) {
        return originalDriver.findElementByName(name);
    }

    @Override
    public UiElement findElementByTag(String tag) {
        return originalDriver.findElementByTag(tag);
    }

    @Override
    public void navigateToWithParams(String url, Params params) {
        originalDriver.navigateToWithParams(hostName + url, params);
    }

    @Override
    public UiSelect findSelectByName(String name) {
        return originalDriver.findSelectByName(name);
    }

    @Override
    public UiElement findElementById(String id) {
        return originalDriver.findElementById(id);
    }
}
