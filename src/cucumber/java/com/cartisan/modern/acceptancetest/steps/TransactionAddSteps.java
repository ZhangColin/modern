package com.cartisan.modern.acceptancetest.steps;

import com.cartisan.modern.acceptancetest.data.transaction.EditableTransaction;
import com.cartisan.modern.acceptancetest.data.transaction.TransactionForTest;
import com.cartisan.modern.acceptancetest.data.transaction.TransactionRepositoryForTest;
import com.cartisan.modern.acceptancetest.pages.AddTransactionPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.cartisan.modern.acceptancetest.steps.AssertionHelper.assertListDeepEquals;

public class TransactionAddSteps {
    @Autowired
    private AddTransactionPage addTransactionPage;

    @Autowired
    private TransactionRepositoryForTest transactionRepository;

    @When("^add transactions with the following information$")
    public void add_transactions_with_the_following_information(List<EditableTransaction> editableTransactions) throws Throwable {
        editableTransactions.forEach(transaction -> addTransactionPage.add(transaction));
    }

    @Then("^the following transactions will be created$")
    public void the_following_transactions_will_be_created(List<TransactionForTest> expected) throws Throwable {
        assertListDeepEquals(expected, transactionRepository.findAll());
    }


}
