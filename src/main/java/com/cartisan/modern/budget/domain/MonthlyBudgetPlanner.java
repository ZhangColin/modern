package com.cartisan.modern.budget.domain;

import com.cartisan.modern.budget.repository.MonthlyBudgetRepository;
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
            saveMonthlyBudget(monthlyBudget);
            afterSuccess.run();
        } catch (IllegalArgumentException e) {
            afterFail.run();
        }
    }

    private void saveMonthlyBudget(MonthlyBudget monthlyBudget) {
        MonthlyBudget existingBudget = monthlyBudgetRepository.findByMonth(monthlyBudget.getMonth());
        if (existingBudget!=null) {
            existingBudget.setBudget(monthlyBudget.getBudget());
            monthlyBudgetRepository.save(existingBudget);
        }
        else{
            monthlyBudgetRepository.save(monthlyBudget);
        }
    }
}
