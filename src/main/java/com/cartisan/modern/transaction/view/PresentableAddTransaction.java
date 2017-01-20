package com.cartisan.modern.transaction.view;

import com.cartisan.modern.common.view.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.cartisan.modern.transaction.domain.Transaction.Type.values;

@Component
public class PresentableAddTransaction {

    private final Model model;

    @Autowired
    public PresentableAddTransaction(Model model) {
        this.model = model;
    }

    public void display() {
        model.addAttribute("types", values());
    }
}
