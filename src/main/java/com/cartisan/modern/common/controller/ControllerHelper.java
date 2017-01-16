package com.cartisan.modern.common.controller;

import org.springframework.ui.Model;

public class ControllerHelper {
    public static Runnable thenSetMessage(Model model, String message){
        return () -> model.addAttribute("message", message);
    }

    public static void setMessage(Model model, String message) {
        model.addAttribute("message", message);
    }
}
