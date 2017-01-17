package com.cartisan.modern.common.controller;

import com.cartisan.modern.common.view.Message;
import org.springframework.ui.Model;

public class ControllerHelper {
    public static Runnable thenSetMessage(Model model, String message){
        return () -> new Message(model, message);
    }
}
