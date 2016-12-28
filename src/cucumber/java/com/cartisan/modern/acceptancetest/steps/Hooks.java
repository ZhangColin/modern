package com.cartisan.modern.acceptancetest.steps;

import com.cartisan.modern.Application;
import com.cartisan.modern.acceptancetest.driver.UiDriver;
import com.cartisan.modern.budget.MonthlyBudgetRepository;
import com.cartisan.modern.user.UserRepository;
import cucumber.api.java.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest
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
