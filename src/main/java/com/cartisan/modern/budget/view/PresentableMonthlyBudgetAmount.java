package com.cartisan.modern.budget.view;

import com.cartisan.modern.common.view.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import static com.cartisan.modern.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;
import static java.lang.String.format;

@Component
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class PresentableMonthlyBudgetAmount {
    private final Model model;
    @Value("${monthlybudget.totalamount.amount}")
    String message;

    @Autowired
    public PresentableMonthlyBudgetAmount(Model model) {
        this.model = model;
    }

    public void display(long amount) {
        model.addAttribute("amount", format(message, amount));
    }
}
