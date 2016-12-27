package com.cartisan.modern.budget;

import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Transactional
public interface MonthlyBudgetRepository extends Repository<MonthlyBudget, Long> {
    MonthlyBudget findByMonth(Date monthDate);
    Iterable<MonthlyBudget> findAll();
    void save(MonthlyBudget monthlyBudget);
    int count();
    void deleteAll();
}
