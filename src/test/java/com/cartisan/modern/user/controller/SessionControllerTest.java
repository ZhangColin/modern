package com.cartisan.modern.user.controller;

import com.cartisan.modern.user.domain.AuthenticationResult;
import com.cartisan.modern.user.view.SignInView;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static com.cartisan.modern.user.builder.AuthenticationResultBuilder.defaultAuthenticationResult;
import static com.cartisan.modern.user.builder.SignInViewBuilder.defaultSignInView;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SessionControllerTest {
    AuthenticationResult authenticationResult = defaultAuthenticationResult().build();
    SignInView signInView = spy(defaultSignInView().build());
    SessionController controller = new SessionController(signInView);

    @Test
    public void should_display_view() {
        assertThat(signIn()).isInstanceOf(SignInView.class);
    }

    @Test
    public void should_pass_error_and_logout_to_view() {
        spyOnDisplayOfSignInView();

        signIn();

        verify(signInView).display(authenticationResult);
    }

    private void spyOnDisplayOfSignInView() {
        doNothing().when(signInView).display(any(AuthenticationResult.class));
    }

    private ModelAndView signIn() {
        return controller.signIn(authenticationResult);
    }
}
