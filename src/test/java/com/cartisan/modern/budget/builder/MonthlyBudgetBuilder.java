package com.cartisan.modern.budget.builder;

import com.cartisan.modern.budget.domain.MonthlyBudget;

import static com.cartisan.modern.budget.domain.MonthlyBudget.builder;
import static com.cartisan.modern.common.Formats.parseMonth;

public class MonthlyBudgetBuilder {
    public static MonthlyBudget.MonthlyBudgetBuilder defaultMonthlyBudget() {
        return builder().month(parseMonth("2015-07")).budget(200);
    }

    public static MonthlyBudget monthlyBudget(String month, int budget) {
        return builder().month(parseMonth(month)).budget(budget).build();
    }
}
