package com.cartisan.modern.common.view;

import org.springframework.ui.Model;

public class Message {
    public Message(Model model, String message) {
        model.addAttribute("message", message);
    }
}
