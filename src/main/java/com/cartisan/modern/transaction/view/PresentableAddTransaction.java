package com.cartisan.modern.transaction.view;

import com.cartisan.modern.transaction.domain.Transaction;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import static com.cartisan.modern.common.view.Messages.RESULT_MESSAGES_FULL_NAME;

@Component
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class PresentableAddTransaction {
    public void display(Model model, Transaction.Type[] values) {
        model.addAttribute("types", values);
    }
}
