package com.cartisan.modern.acceptancetest.steps;

import com.cartisan.modern.acceptancetest.data.Messages;
import com.cartisan.modern.acceptancetest.pages.CommonPage;
import com.cartisan.modern.acceptancetest.pages.HomePage;
import com.cartisan.modern.acceptancetest.pages.SignInPage;
import com.cartisan.modern.user.domain.User;
import com.cartisan.modern.user.repository.UserRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class SignInSteps {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommonPage commonPage;

    @Autowired
    private SignInPage signInPage;

    @Autowired
    private HomePage homePage;

    @Autowired
    private Messages messages;

    @Given("^there is a user named \"([^\"]*)\" and password is \"([^\"]*)\"$")
    public void there_is_a_user_named_and_password_is(String userName, String password) {
        userRepository.save(new User(userName, password));
    }

    @When("^login with user name \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void login_with_a_user_name_and_password(String userName, String password) {
        signInPage.signIn(userName, password);
    }

    @Then("^login successfully$")
    public void login_successfully() {
        assertThat(commonPage.getAllText()).contains(messages.welcome);
    }

    @Then("^login failed with some message$")
    public void login_failed_with_some_message() throws Throwable {
        assertThat(commonPage.getAllText()).contains(messages.loginFailed);
    }

    @When("^logout$")
    public void logout() throws Throwable {
        homePage.signout();
    }

    @Then("^logout with some message$")
    public void logout_with_some_message() throws Throwable {
        assertThat(commonPage.getAllText()).contains(messages.logout);
    }

}
