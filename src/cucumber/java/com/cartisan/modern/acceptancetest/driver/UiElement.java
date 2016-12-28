package com.cartisan.modern.acceptancetest.driver;

public interface UiElement {
    void sendKeys(String keys);

    void submit();

    String getText();
}
