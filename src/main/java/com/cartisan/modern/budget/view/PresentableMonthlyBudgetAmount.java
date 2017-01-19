package com.cartisan.modern.budget.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import static com.cartisan.modern.common.view.Messages.RESULT_MESSAGES;
import static java.lang.String.format;

@Component
@PropertySource(RESULT_MESSAGES)
public class PresentableMonthlyBudgetAmount {
    @Value("${monthlybudget.totalamount.amount}")
    String message;

    public void display(Model model, long amount) {
        model.addAttribute("amount", format(message, amount));
    }
}
