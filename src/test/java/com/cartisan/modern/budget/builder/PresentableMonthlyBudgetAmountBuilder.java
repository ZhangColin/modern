package com.cartisan.modern.budget.builder;

import com.cartisan.modern.budget.view.PresentableMonthlyBudgetAmount;

import static com.cartisan.modern.budget.view.PresentableMonthlyBudgetAmount.builder;

public class PresentableMonthlyBudgetAmountBuilder {
    public static PresentableMonthlyBudgetAmount.PresentableMonthlyBudgetAmountBuilder defaultPresentableMonthlyBudgetAmount() {
        return builder().message("whatever message");
    }
}
