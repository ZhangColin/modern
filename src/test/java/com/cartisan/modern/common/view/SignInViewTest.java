package com.cartisan.modern.common.view;

import org.junit.Test;

import static com.cartisan.modern.common.builder.SignInViewBuilder.defaultSignInView;
import static com.cartisan.modern.common.controller.Urls.SIGNIN;
import static org.assertj.core.api.Assertions.assertThat;

public class SignInViewTest {
    SignInView view;

    @Test
    public void message_when_sign_in_error() {
        view = defaultSignInView().failedMessage("a failed message").build();

        view.display("any error", null);

        assertMessageEquals("a failed message");
        assertTypeEquals("danger");
    }

    @Test
    public void message_when_logout() {
        view = defaultSignInView().logoutMessage("a logout message").build();

        view.display(null, "something logout");

        assertMessageEquals("a logout message");
        assertTypeEquals("info");
    }

    @Test
    public void should_go_to_sign_in_view(){
        view = defaultSignInView().build();
        assertThat(view.display("any error", "any logout").getViewName()).isEqualTo(SIGNIN);
    }

    private void assertMessageEquals(String message) {
        assertThat(view.getModel().get("message")).isEqualTo(message);
    }

    private void assertTypeEquals(String type) {
        assertThat(view.getModel().get("type")).isEqualTo(type);
    }
}
