package com.cartisan.modern.acceptancetest.driver;

import com.cartisan.modern.common.view.Params;

public interface UiDriver {
    void close();

    void navigateTo(String url);

    UiElement findElementByName(String name);

    UiElement findElementByTag(String tag);

    void navigateToWithParams(String url, Params params);

    UiSelect findSelectByName(String name);

    UiElement findElementById(String id);
}
