package com.cartisan.modern.acceptancetest.data.budget;

import com.cartisan.modern.budget.MonthlyBudget;
import org.springframework.data.repository.Repository;

public interface MonthlyBudgetRepositoryForTest extends Repository<MonthlyBudget, Long> {
    void deleteAll();
    void save(MonthlyBudget monthlyBudget);
    int count();
    Iterable<MonthlyBudget> findAll();
}
