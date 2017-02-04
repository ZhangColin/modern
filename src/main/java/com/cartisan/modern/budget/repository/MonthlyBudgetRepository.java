package com.cartisan.modern.budget.repository;

import com.cartisan.modern.budget.domain.MonthlyBudget;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
public interface MonthlyBudgetRepository extends Repository<MonthlyBudget, Long> {
    MonthlyBudget findByMonth(LocalDate monthDate);

    Iterable<MonthlyBudget> findAll();

    void save(MonthlyBudget monthlyBudget);
}
