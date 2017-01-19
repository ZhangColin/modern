package com.cartisan.modern.transaction.view;

import com.cartisan.modern.transaction.domain.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class PresentableAddTransaction {
    public void display(Model model, Transaction.Type[] values) {
        model.addAttribute("types", values);
    }
}
