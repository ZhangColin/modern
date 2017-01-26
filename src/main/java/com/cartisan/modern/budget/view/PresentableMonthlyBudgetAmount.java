package com.cartisan.modern.budget.view;

import com.cartisan.modern.common.view.View;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static com.cartisan.modern.common.controller.Urls.MONTHLYBUDGET_TOTALAMOUNT;
import static com.cartisan.modern.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;
import static java.lang.String.format;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class PresentableMonthlyBudgetAmount extends ModelAndView implements View<Long> {

    public final String message;

    @Builder
    public PresentableMonthlyBudgetAmount(@Value("${monthlybudget.totalamount.amount}") String message) {
        this.message = message;
        setViewName(MONTHLYBUDGET_TOTALAMOUNT);
    }

    @Override
    public void display(Long amount) {
        addObject("amount", format(message, amount));
    }
}
