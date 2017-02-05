package com.cartisan.modern.acceptancetest.steps;

import com.cartisan.modern.acceptancetest.data.account.AccountRepositoryForTest;
import com.cartisan.modern.acceptancetest.pages.CommonPage;
import com.cartisan.modern.acceptancetest.pages.ShowAllAccountsPage;
import com.cartisan.modern.account.domain.Account;
import com.cartisan.modern.account.view.PresentableAccount;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountListSteps {
    @Autowired
    AccountRepositoryForTest accountRepository;

    @Autowired
    ShowAllAccountsPage showAllAccountsPage;

    @Autowired
    CommonPage commonPage;

    @Given("^exists the following accounts$")
    public void exists_the_following_accounts(List<Account> accounts) throws Throwable {
        accounts.forEach(accountRepository::save);
    }

    @When("^show all accounts$")
    public void show_all_accounts() throws Throwable {
        showAllAccountsPage.show();
    }

    @Then("^you will see all accounts as below$")
    public void you_will_see_all_accounts_as_below(List<PresentableAccount> expected) throws Throwable {
        expected.forEach(presentableAccount -> assertThat(commonPage.getAllText()).contains(presentableAccount.allViewText()));
    }

}
