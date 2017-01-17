package com.cartisan.modern.transaction.view;

import com.cartisan.modern.transaction.domain.Transaction;
import org.springframework.ui.Model;

public class Types {
    public Types(Model model, Transaction.Type[] types) {
        model.addAttribute("types", types);
    }
}
