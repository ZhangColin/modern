package com.cartisan.modern.acceptancetest.steps;


import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

public class GetAmountSteps implements En {
    WebDriver driver;

    {
        Given("^no budget for any month$", () -> {
        });
        When("^get amount of period from \"([^\"]*)\" to \"([^\"]*)\"$", (String startDate, String endDate) -> {
            driver = new FirefoxDriver();
            driver.get("http://localhost:8080/get_amount?startDate=" + startDate + "&endDate=" + endDate);
        });
        Then("^the amount is (\\d+)$", (Integer arg1) -> {
            assertEquals("Modern", driver.getTitle());
            driver.close();
        });
    }
}
