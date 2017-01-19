package com.cartisan.modern.budget.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.cartisan.modern.common.view.Messages.RESULT_MESSAGES_FULL_NAME;
import static java.lang.String.format;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class PresentableMonthlyBudgetAmount {
    private final HttpServletRequest request;
    @Value("${monthlybudget.totalamount.amount}")
    String message;

    @Autowired
    public PresentableMonthlyBudgetAmount(HttpServletRequest request) {
        this.request = request;
    }

    public void display(long amount) {
        request.setAttribute("amount", format(message, amount));
    }
}
