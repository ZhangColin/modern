package com.cartisan.modern.acceptancetest.steps;

import com.cartisan.modern.acceptancetest.data.transaction.EditableTransaction;
import com.cartisan.modern.acceptancetest.data.transaction.TransactionRepositoryForTest;
import com.cartisan.modern.acceptancetest.pages.AddTransactionPage;
import com.cartisan.modern.transaction.domain.Transaction;
import cucumber.api.Format;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.cartisan.modern.acceptancetest.steps.AssertionHelper.assertListDeepEquals;
import static com.cartisan.modern.common.Formats.DAY;

public class AddTransactionSteps {
    @Autowired
    private AddTransactionPage addTransactionPage;

    @Autowired
    private TransactionRepositoryForTest transactionRepository;

    @When("^add transactions with the following information$")
    public void add_transactions_with_the_following_information(List<EditableTransaction> editableTransactions) throws Throwable {
        editableTransactions.forEach(transaction->addTransactionPage.add(transaction));
    }

    @Then("^the following transactions will be created$")
    public void the_following_transactions_will_be_created(@Format(DAY) List<Transaction> expected) throws Throwable {
        assertTransactionEquals(expected, transactionRepository.findAll());
    }

    private void assertTransactionEquals(List<Transaction> expected,
                                         List<Transaction> actual) {
        assertListDeepEquals(expected, actual);
    }

}
