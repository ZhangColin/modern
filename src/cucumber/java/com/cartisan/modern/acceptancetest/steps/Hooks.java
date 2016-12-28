package com.cartisan.modern.acceptancetest.steps;

import com.cartisan.modern.acceptancetest.driver.UiDriver;
import com.cartisan.modern.budget.MonthlyBudgetRepository;
import com.cartisan.modern.user.UserRepository;
import cucumber.api.java.After;
import org.springframework.beans.factory.annotation.Autowired;

public class Hooks {
    @Autowired
    private UiDriver uiDriver;

    @After
    public void closeUiDriver(){
        uiDriver.close();
    }

    @Autowired
    private MonthlyBudgetRepository monthlyBudgetRepository;

    @After("@monthlyBudget")
    public void cleanUpMonthlyBudget(){
        monthlyBudgetRepository.deleteAll();
    }

    @Autowired
    private UserRepository userRepository;

    @After("@user")
    public void cleanUpUser(){
        userRepository.deleteAll();
    }
}
