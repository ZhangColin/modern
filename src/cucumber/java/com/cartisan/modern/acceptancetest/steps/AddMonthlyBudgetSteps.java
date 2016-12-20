package com.cartisan.modern.acceptancetest.steps;


import cucumber.api.java8.En;

/**
 * Created by colin on 2016/12/20.
 */
public class AddMonthlyBudgetSteps implements En {
    {
        When("^add budget for \"([^\"]*)\" with amount (\\d+)$", (String arg1, Integer arg2) -> {
        });

        Then("^monthly budget (\\d+) for \"([^\"]*)\" is saved$", (Integer arg1, String arg2) -> {
        });

    }
}
