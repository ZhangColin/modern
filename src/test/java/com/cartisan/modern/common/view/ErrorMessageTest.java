package com.cartisan.modern.common.view;

import org.junit.Test;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;

import static com.cartisan.modern.common.builder.FieldErrorDataMother.fieldError;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class ErrorMessageTest {
    @Test
    public void should_pass_error_message_to_page(){
        MessageSource stubMessageSource = stubMessageSourceWithErrorMessage("error message");
        HttpServletRequest mockHttpServletRequest = mock(HttpServletRequest.class);
        ErrorMessage errorMessage = new ErrorMessage(stubMessageSource, mockHttpServletRequest);

        errorMessage.display(fieldError("field"));

        verify(mockHttpServletRequest).setAttribute("error.field", "error message");
    }

    private MessageSource stubMessageSourceWithErrorMessage(String errorMessage) {
        MessageSource stubMessageSource = mock(MessageSource.class);
        when(stubMessageSource.getMessage(any(FieldError.class), eq(null))).thenReturn(errorMessage);
        return stubMessageSource;
    }
}
