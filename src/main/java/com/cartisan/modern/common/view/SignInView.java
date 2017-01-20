package com.cartisan.modern.common.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

import static com.cartisan.modern.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value="request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class SignInView {
    private final HttpServletRequest request;
    @Value("${authentication.failed}")
    String failedMessage;

    @Value("${authentication.logout}")
    String logoutMessage;

    public SignInView(HttpServletRequest request) {
        this.request = request;
    }

    public void display(String error, String logout) {
        if (error != null) {
            setMessageAndType( failedMessage, "danger");
        }

        if (logout != null) {
            setMessageAndType( logoutMessage, "info");
        }
    }

    private void setMessageAndType(String failedMessage, String danger) {
        request.setAttribute("message", failedMessage);
        request.setAttribute("type", danger);
    }
}
