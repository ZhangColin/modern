package com.cartisan.modern.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MonthlyBudgetPlanner {
    private BudgetCategory budgetCategory;

    @Autowired
    public MonthlyBudgetPlanner(BudgetCategory budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public long getAmount(Date startDate, Date endDate) {
        return budgetCategory.getAmount(startDate, endDate);
    }
}
