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

import static com.cartisan.modern.acceptancetest.steps.Formats.DAY;
import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static org.unitils.reflectionassert.ReflectionComparatorMode.IGNORE_DEFAULTS;

public class AddTransactionSteps {
    @Autowired
    private AddTransactionPage addTransactionPage;

    @Autowired
    private TransactionRepositoryForTest transactionRepository;

    @When("^add a new transaction with the following information$")
    public void add_a_new_transaction_with_the_following_information(List<EditableTransaction> editableTransactions) throws Throwable {
        addTransactionPage.add(editableTransactions.get(0));
    }

    @Then("^a new transaction will be created$")
    public void a_new_transaction_will_be_created(@Format(DAY) List<Transaction> expected) throws Throwable {
        assertTransactionEquals(expected, transactionRepository.findAll());
    }

    private void assertTransactionEquals(List<Transaction> expected,
                                         List<Transaction> actual) {
        assertEquals(1, actual.size());
        assertReflectionEquals(expected, actual, IGNORE_DEFAULTS);
    }

}
