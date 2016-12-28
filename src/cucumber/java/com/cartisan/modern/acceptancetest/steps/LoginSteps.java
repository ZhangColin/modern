package com.cartisan.modern.acceptancetest.steps;

import com.cartisan.modern.acceptancetest.driver.UiDriver;
import com.cartisan.modern.acceptancetest.driver.UiElement;
import com.cartisan.modern.acceptancetest.pages.CommonPage;
import com.cartisan.modern.acceptancetest.pages.LoginPage;
import com.cartisan.modern.user.User;
import com.cartisan.modern.user.UserRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommonPage commonPage;

    @Autowired
    private LoginPage page;

    @Given("^there is a user named \"([^\"]*)\" and password is \"([^\"]*)\"$")
    public void there_is_a_user_named_and_password_is(String userName, String password) {
        userRepository.save(new User(userName, password));
    }

    @When("^login with user name \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void login_with_a_user_name_and_password(String userName, String password) {
        page.login(userName, password);
    }

    @Then("^login successfully$")
    public void login_successfully() {
        assertTrue(commonPage.getAllText().contains("Welcome"));
    }

}
