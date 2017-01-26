package com.cartisan.modern.budget.domain;

import com.cartisan.modern.budget.repository.MonthlyBudgetRepository;
import com.cartisan.modern.common.callback.PostActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.cartisan.modern.common.callback.PostActionsFactory.failed;
import static com.cartisan.modern.common.callback.PostActionsFactory.success;

@Service
public class MonthlyBudgetPlanner {
    private final BudgetCategory budgetCategory;
    private final MonthlyBudgetRepository monthlyBudgetRepository;

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

    public PostActions addMonthlyBudget(MonthlyBudget monthlyBudget) {
        try {
            saveMonthlyBudget(monthlyBudget);
            return success();
        } catch (IllegalArgumentException e) {
            return failed();
        }
    }

    private void saveMonthlyBudget(MonthlyBudget monthlyBudget) {
        MonthlyBudget existingBudget = monthlyBudgetRepository.findByMonth(monthlyBudget.getMonth());
        if (existingBudget != null) {
            existingBudget.setBudget(monthlyBudget.getBudget());
            monthlyBudgetRepository.save(existingBudget);
        } else {
            monthlyBudgetRepository.save(monthlyBudget);
        }
    }
}
