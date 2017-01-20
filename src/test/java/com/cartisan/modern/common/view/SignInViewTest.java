package com.cartisan.modern.common.view;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SignInViewTest {
    private HttpServletRequest mockHttpServletRequest = mock(HttpServletRequest.class);
    private SignInView view = new SignInView(mockHttpServletRequest);

    @Test
    public void message_when_sign_in_error() {
        view.failedMessage = "a failed message";

        view.display("any error", null);

        verify(mockHttpServletRequest).setAttribute("message", "a failed message");
        verify(mockHttpServletRequest).setAttribute("type", "danger");
    }

    @Test
    public void message_when_logout() {
        view.logoutMessage = "a logout message";

        view.display(null, "something logout");

        verify(mockHttpServletRequest).setAttribute("message", "a logout message");
        verify(mockHttpServletRequest).setAttribute("type", "info");
    }
}
