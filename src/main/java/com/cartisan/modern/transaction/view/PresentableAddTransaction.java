package com.cartisan.modern.transaction.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static com.cartisan.modern.common.controller.Urls.TRANSACTIONS_ADD;
import static com.cartisan.modern.transaction.domain.Transaction.Type.values;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
public class PresentableAddTransaction extends ModelAndView {
    public PresentableAddTransaction() {
        addObject("types", values());
        setViewName(TRANSACTIONS_ADD);
    }
}
