package com.cartisan.modern.acceptancetest.steps;

import com.cartisan.modern.Application;
import cucumber.api.java8.En;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest
public class LoginSteps implements En {
    {
        Given("^there is a user named \"([^\"]*)\" and password is \"([^\"]*)\"$", (String arg1, String arg2) -> {
        });

        When("^login with user name \"([^\"]*)\" and password \"([^\"]*)\"$", (String arg1, String arg2) -> {
            WebDriver driver = new FirefoxDriver();
            driver.get("http://localhost:8080/login");
        });

        Then("^login successfully$", () -> {
        });

    }

}
