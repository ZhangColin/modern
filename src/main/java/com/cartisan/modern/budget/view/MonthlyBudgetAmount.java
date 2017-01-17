package com.cartisan.modern.budget.view;

import org.springframework.ui.Model;

public class MonthlyBudgetAmount {
    public MonthlyBudgetAmount(Model model, long amount) {
        model.addAttribute("amount", amount);
    }
}
