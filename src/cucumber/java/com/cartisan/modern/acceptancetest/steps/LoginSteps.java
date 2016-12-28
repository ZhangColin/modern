package com.cartisan.modern.acceptancetest.steps;

import com.cartisan.modern.Application;
import com.cartisan.modern.acceptancetest.driver.UiDriver;
import com.cartisan.modern.user.User;
import com.cartisan.modern.user.UserRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest
public class LoginSteps{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UiDriver driver;

    @Given("^there is a user named \"([^\"]*)\" and password is \"([^\"]*)\"$")
    public void there_is_a_user_named_and_password_is(String userName, String password) {
        userRepository.save(new User(userName, password));
    }

    @When("^login with user name \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void login_with_a_user_name_and_password(String userName, String password) {
        driver.getWebDriver().get("http://localhost:8080/login");
        WebElement userNameTextBox = driver.getWebDriver().findElement(By.name("username"));
        userNameTextBox.sendKeys(userName);
        WebElement passwordBox = driver.getWebDriver().findElement(By.name("password"));
        passwordBox.sendKeys(password);
        userNameTextBox.submit();
    }

    @Then("^login successfully$")
    public void login_successfully() {
        String bodyText = driver.getWebDriver().findElement(By.tagName("body")).getText();
        assertTrue(bodyText.contains("Welcome"));

        userRepository.deleteAll();
    }
}
