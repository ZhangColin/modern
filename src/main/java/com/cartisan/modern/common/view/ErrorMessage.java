package com.cartisan.modern.common.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;

public class ErrorMessage {

    private final MessageSource messageSource;
    private final HttpServletRequest request;

    @Autowired
    public ErrorMessage(MessageSource messageSource, HttpServletRequest request) {
        this.messageSource = messageSource;
        this.request = request;
    }

    public void display(FieldError fieldError) {
        request.setAttribute("error." + fieldError.getField(), messageSource.getMessage(fieldError, null));
    }
}
