package com.cartisan.modern.account.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static com.cartisan.modern.common.controller.Urls.ACCOUNTS_ADD;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
public class PresentableAddAccount extends ModelAndView {
    public PresentableAddAccount() {
        setViewName(ACCOUNTS_ADD);
    }
}
