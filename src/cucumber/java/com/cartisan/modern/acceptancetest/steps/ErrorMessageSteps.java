package com.cartisan.modern.acceptancetest.steps;

import com.cartisan.modern.acceptancetest.data.ErrorMessages;
import com.cartisan.modern.acceptancetest.pages.CommonPage;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrorMessageSteps {
    @Autowired
    private CommonPage commonPage;

    @Autowired
    private ErrorMessages errorMessages;


    @Then("^there is an error message for empty ([^\"]*)$")
    public void there_is_an_error_message_for_empty_input(String field) throws Throwable {
        assertThat(commonPage.getAllText()).containsIgnoringCase(errorMessageWith(field, errorMessages.notEmpty));
    }

    @Then("^there is an error message for null ([^\"]*)$")
    public void there_is_an_error_message_for_null_input(String field) throws Throwable {
        assertThat(commonPage.getAllText()).containsIgnoringCase(errorMessageWith(field, errorMessages.notNull));
    }

    @Then("^there is an error message for invalid date ([^\"]*)$")
    public void there_is_an_error_message_for_invalid_date_input(String field) throws Throwable {
        assertThat(commonPage.getAllText()).containsIgnoringCase(errorMessageWith(field, errorMessages.invalidDate));
    }

    @Then("^there is an error message for invalid number ([^\"]*)$")
    public void there_is_an_error_message_for_invalid_number(String field) {
        assertThat(commonPage.getAllText()).containsIgnoringCase(errorMessageWith(field, errorMessages.invalidNumber));
    }

    private String errorMessageWith(String field, String error) {
        return String.format("%s %s", field, error);
    }

}
