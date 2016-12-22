package com.cartisan.modern.budget;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;

@Transactional
public interface MonthlyBudgetRepository extends CrudRepository<MonthlyBudget, Long>{
    MonthlyBudget findByMonth(Date monthDate);
}
