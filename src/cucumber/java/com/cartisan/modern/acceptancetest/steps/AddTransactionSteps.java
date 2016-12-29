package com.cartisan.modern.acceptancetest.steps;

import com.cartisan.modern.acceptancetest.pages.AddTransactionPage;
import com.cartisan.modern.transaction.repository.TransactionRepository;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class AddTransactionSteps {
    @Autowired
    private AddTransactionPage addTransactionPage;

    @Autowired
    private TransactionRepository transactionRepository;

    @When("^add a new transaction with the following information$")
    public void add_a_new_transaction_with_the_following_information(List<Transaction> transactions) throws Throwable {
        addTransactionPage.add(transactions.get(0));
    }

    @Then("^a new transaction will be created$")
    public void a_new_transaction_will_be_created(List<Transaction> expected) throws Throwable {
        List<com.cartisan.modern.transaction.domain.Transaction> actual = transactionRepository.findAll();
        assertEquals(expected.size(), actual.size());
        IntStream.range(0, expected.size()).forEach(i->{
            assertEquals(expected.get(i).getType(), actual.get(i).getType().name());
            assertEquals(expected.get(i).getAmount(), actual.get(i).getAmount().toString());
            assertEquals(expected.get(i).getDescription(), actual.get(i).getDescription());
            assertEquals(expected.get(i).getDate(),
                    new SimpleDateFormat("yyyy-MM-dd").format(actual.get(i).getDate()));
        });
        transactionRepository.deleteAll();
    }

}
