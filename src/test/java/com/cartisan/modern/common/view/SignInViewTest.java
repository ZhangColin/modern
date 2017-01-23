package com.cartisan.modern.common.view;

import org.junit.Test;

import static com.cartisan.modern.common.controller.Urls.SIGNIN;
import static org.assertj.core.api.Assertions.assertThat;

public class SignInViewTest {
    private SignInView view = new SignInView();

    @Test
    public void message_when_sign_in_error() {
        view.failedMessage = "a failed message";

        view.display("any error", null);

        assertMessageEquals("a failed message");
        assertTypeEquals("danger");
    }

    @Test
    public void message_when_logout() {
        view.logoutMessage = "a logout message";

        view.display(null, "something logout");

        assertMessageEquals("a logout message");
        assertTypeEquals("info");
    }

    @Test
    public void should_go_to_sign_in_view(){
        assertThat(view.display("any error", "any logout").getViewName()).isEqualTo(SIGNIN);
    }

    private void assertMessageEquals(String message) {
        assertThat(view.getModel().get("message")).isEqualTo(message);
    }

    private void assertTypeEquals(String type) {
        assertThat(view.getModel().get("type")).isEqualTo(type);
    }
}
