package com.cartisan.modern.budget.repository;

import com.cartisan.modern.budget.domain.MonthlyBudget;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Transactional
public interface MonthlyBudgetRepository extends Repository<MonthlyBudget, Long> {
    MonthlyBudget findByMonth(Date monthDate);
    Iterable<MonthlyBudget> findAll();
    void save(MonthlyBudget monthlyBudget);
}
