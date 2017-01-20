package com.cartisan.modern.common.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
public class RequestBasedModel implements Model {

    private final HttpServletRequest request;

    @Autowired
    public RequestBasedModel(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void addAttribute(String name, Object value) {
        request.setAttribute(name, value);
    }
}
