package com.cartisan.modern.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MonthlyBudgetPlanner {
    private BudgetCategory budgetCategory;
    private MonthlyBudgetRepository monthlyBudgetRepository;

    @Autowired
    public MonthlyBudgetPlanner(BudgetCategory budgetCategory, MonthlyBudgetRepository monthlyBudgetRepository) {
        this.budgetCategory = budgetCategory;
        this.monthlyBudgetRepository = monthlyBudgetRepository;
    }

    public long getAmount(Date startDate, Date endDate) {
        allPlannedBudgets().forEach(this::setAmount);

        return getTotalAmount(startDate, endDate);
    }

    private long getTotalAmount(Date startDate, Date endDate) {
        return budgetCategory.getAmount(startDate, endDate);
    }

    private void setAmount(MonthlyBudget monthlyBudget) {
        budgetCategory.setAmount(monthlyBudget.getMonth(), monthlyBudget.getBudget());
    }

    private Iterable<MonthlyBudget> allPlannedBudgets() {
        return monthlyBudgetRepository.findAll();
    }

    public void addMonthlyBudget(MonthlyBudget monthlyBudget, Runnable afterSuccess, Runnable afterFail) {
        try {
            monthlyBudgetRepository.save(monthlyBudget);
            afterSuccess.run();
        } catch (IllegalArgumentException e) {
            afterFail.run();
        }
    }
}
