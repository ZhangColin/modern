package com.cartisan.modern.acceptancetest.steps;

import com.cartisan.modern.acceptancetest.data.Messages;
import com.cartisan.modern.acceptancetest.data.account.AccountRepositoryForTest;
import com.cartisan.modern.acceptancetest.data.account.EditableAccount;
import com.cartisan.modern.acceptancetest.pages.AddAccountPage;
import com.cartisan.modern.account.domain.Account;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.cartisan.modern.acceptancetest.steps.AssertionHelper.assertListDeepEquals;
import static com.cartisan.modern.account.builder.AccountBuilder.defaultAccount;

public class AccountAddSteps {
    @Autowired
    AddAccountPage addAccountPage;

    @Autowired
    AccountRepositoryForTest accountRepositoryForTest;

    @Autowired
    Messages messages;

    @When("^add account with the following information$")
    public void add_account_with_the_following_information(List<EditableAccount> accounts) throws Throwable {
        accounts.forEach(addAccountPage::add);
    }

    @Then("^the following account will be created$")
    public void the_following_account_will_be_created(List<Account> expected) throws Throwable {
        assertListDeepEquals(expected, accountRepositoryForTest.findAll());
    }


    @Given("^existing an account with name \"([^\"]*)\"$")
    public void existing_an_account_with_name(String name) throws Throwable {
        accountRepositoryForTest.save(defaultAccount().name(name).build());
    }

    @When("^add a new account with name \"([^\"]*)\"$")
    public void add_a_new_account_with_name(String name) throws Throwable {
        addAccountPage.add(editableAccount(name));
    }

    private EditableAccount editableAccount(String name) {
        EditableAccount account = new EditableAccount();
        account.setName(name);
        account.setBalanceBroughtForward("0");
        return account;
    }

}
