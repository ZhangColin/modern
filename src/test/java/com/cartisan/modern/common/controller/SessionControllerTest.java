package com.cartisan.modern.common.controller;

import com.cartisan.modern.common.view.SignInView;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static com.cartisan.modern.common.builder.SignInViewBuilder.defaultSignInView;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SessionControllerTest {
    SignInView signInView = spy(defaultSignInView().build());
    SessionController controller = new SessionController(signInView);

    @Test
    public void should_display_view() {
        assertThat(signIn()).isInstanceOf(SignInView.class);
    }

    @Test
    public void should_pass_error_and_logout_to_view() {
        spyOnDisplayOfSignInView();
        controller.signIn("error", "logout");

        verify(signInView).display("error", "logout");
    }

    private void spyOnDisplayOfSignInView() {
        doReturn(signInView).when(signInView).display(anyString(), anyString());
    }

    private ModelAndView signIn() {
        return controller.signIn("error", "logout");
    }
}
