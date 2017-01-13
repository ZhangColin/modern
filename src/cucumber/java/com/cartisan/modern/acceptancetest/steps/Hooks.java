package com.cartisan.modern.acceptancetest.steps;

import com.cartisan.modern.Application;
import com.cartisan.modern.acceptancetest.data.budget.MonthlyBudgetRepositoryForTest;
import com.cartisan.modern.acceptancetest.data.transaction.TransactionRepositoryForTest;
import com.cartisan.modern.acceptancetest.driver.UiDriver;
import com.cartisan.modern.acceptancetest.pages.SignInPage;
import com.cartisan.modern.user.domain.User;
import com.cartisan.modern.user.repository.UserRepository;
import cucumber.api.java.After;
import cucumber.api.java.Before;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepositoryForTest transactionRepository;

    @Autowired
    private MonthlyBudgetRepositoryForTest monthlyBudgetRepository;

    @Autowired
    private SignInPage signInPage;

    @Before("@user")
    public void signIn(){
        userRepository.save(new User("user", "password"));
        signInPage.signIn("user", "password");
    }

    @After
    public void closeUiDriver(){
        uiDriver.close();
    }

    @Before("@monthlyBudget")
    @After("@monthlyBudget")
    public void cleanUpMonthlyBudget(){
        monthlyBudgetRepository.deleteAll();
    }

    @After("@user")
    public void cleanUpUser(){
        userRepository.deleteAll();
    }

    @Before("@transaction")
    @After("@transaction")
    public void cleanUpTransaction(){
        transactionRepository.deleteAll();
    }
}
