package com.cartisan.modern.common.controller;

import com.cartisan.modern.common.view.View;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;

public class ControllerTestHelper {
    public static void spyOnDisplayOf(View view) {
        doNothing().when(view).display(anyObject());
    }
}
