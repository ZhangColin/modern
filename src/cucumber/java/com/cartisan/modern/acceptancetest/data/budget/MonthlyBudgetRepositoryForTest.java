package com.cartisan.modern.acceptancetest.data.budget;

import com.cartisan.modern.budget.domain.MonthlyBudget;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MonthlyBudgetRepositoryForTest extends Repository<MonthlyBudget, Long> {
    void deleteAll();

    void save(MonthlyBudget monthlyBudget);

    List<MonthlyBudget> findAll();
}
