package com.cartisan.modern.common.view;

import com.cartisan.modern.common.controller.SessionController;
import org.junit.Test;

import static com.cartisan.modern.common.controller.Urls.SIGNIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SessionControllerTest {
    private SignInView mockSignInView = mock(SignInView.class);
    private     SessionController controller = new SessionController(mockSignInView);

    @Test
    public void should_go_to_sign_in() {

        assertThat(signInFailed()).isEqualTo(SIGNIN);
    }

    @Test
    public void should_display_view() {
        signInFailed();
        verify(mockSignInView).display("error", "logout");
    }

    private String signInFailed() {
        return controller.signIn("error", "logout");
    }
}
