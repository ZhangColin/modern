package com.cartisan.modern.acceptancetest.steps;

import com.cartisan.modern.acceptancetest.data.transaction.EditableTransaction;
import com.cartisan.modern.acceptancetest.data.transaction.TransactionRepositoryForTest;
import com.cartisan.modern.acceptancetest.pages.AddTransactionPage;
import com.cartisan.modern.acceptancetest.pages.CommonPage;
import com.cartisan.modern.acceptancetest.pages.ShowAllTransactionsPage;
import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.view.PresentableTransaction;
import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static com.cartisan.modern.acceptancetest.steps.AssertionHelper.assertListDeepEquals;
import static com.cartisan.modern.common.Formats.DAY;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionSteps {
    @Autowired
    private AddTransactionPage addTransactionPage;

    @Autowired
    private TransactionRepositoryForTest transactionRepository;

    @Autowired
    private ShowAllTransactionsPage showAllTransactionsPage;

    @Autowired
    private CommonPage commonPage;

    @When("^add transactions with the following information$")
    public void add_transactions_with_the_following_information(List<EditableTransaction> editableTransactions) throws Throwable {
        editableTransactions.forEach(transaction->addTransactionPage.add(transaction));
    }

    @Then("^the following transactions will be created$")
    public void the_following_transactions_will_be_created(@Format(DAY) List<Transaction> expected) throws Throwable {
        assertListDeepEquals(expected, transactionRepository.findAll(), "date");
    }

    @Given("^exists the following transactions$")
    public void exists_the_following_transactions(@Format(DAY) List<Transaction> transactions) throws Throwable {
        transactions.forEach(transaction -> transactionRepository.save(transaction));
    }

    @When("^show all transactions$")
    public void show_all_transactions() throws Throwable {
        showAllTransactionsPage.show();
    }

    @Then("^you will see all transactions as below$")
    public void you_will_see_all_transactions_as_belw(@Format(DAY) List<PresentableTransaction> transactions) throws Throwable {
        transactions.forEach(transaction->assertThat(commonPage.getAllText()).contains(transaction.allViewText()));
    }

    @When("^show total of all transactions$")
    public void show_total_of_all_transactions() throws Throwable {
        show_all_transactions();
    }

    @Then("^you will see the total as below$")
    public void you_will_see_the_total_as_below(Map<String, String> totals) throws Throwable {
        assertThat(commonPage.getAllText()).contains(totals.values());
    }

}
