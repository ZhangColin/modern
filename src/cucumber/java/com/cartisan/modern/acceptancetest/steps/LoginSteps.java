package com.cartisan.modern.acceptancetest.steps;

import com.cartisan.modern.acceptancetest.driver.UiDriver;
import com.cartisan.modern.acceptancetest.driver.UiElement;
import com.cartisan.modern.user.User;
import com.cartisan.modern.user.UserRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

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
        driver.navigateTo("http://localhost:8080/login");
        UiElement userNameTextBox = driver.findElementByName("username");
        userNameTextBox.sendKeys(userName);
        UiElement passwordBox = driver.findElementByName("password");
        passwordBox.sendKeys(password);
        userNameTextBox.submit();
    }

    @Then("^login successfully$")
    public void login_successfully() {
        String bodyText = driver.findElementByTag("body").getText();
        assertTrue(bodyText.contains("Welcome"));
    }
}
