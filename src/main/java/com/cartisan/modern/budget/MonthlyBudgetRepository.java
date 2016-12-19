package com.cartisan.modern.budget;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface MonthlyBudgetRepository extends CrudRepository<MonthlyBudget, Long>{
}
