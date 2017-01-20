package com.cartisan.modern.common.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
public class Message {
    private final HttpServletRequest request;

    @Autowired
    public Message(HttpServletRequest request) {
        this.request = request;
    }

    public void display(String message) {
        request.setAttribute("message", message);
    }
}
