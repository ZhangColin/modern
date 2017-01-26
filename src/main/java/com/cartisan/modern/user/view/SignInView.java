package com.cartisan.modern.user.view;

import com.cartisan.modern.common.view.View;
import com.cartisan.modern.user.domain.AuthenticationResult;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static com.cartisan.modern.common.controller.Urls.SIGNIN;
import static com.cartisan.modern.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGES_FULL_NAME)
@Builder
public class SignInView extends ModelAndView implements View<AuthenticationResult> {
    private final String failedMessage;
    private final String logoutMessage;

    public SignInView(@Value("${authentication.failed}") String failedMessage,
                      @Value("${authentication.logout}") String logoutMessage) {
        this.failedMessage = failedMessage;
        this.logoutMessage = logoutMessage;
        setViewName(SIGNIN);
    }

    private void setMessageAndType(String failedMessage, String danger) {
        addObject("message", failedMessage);
        addObject("type", danger);
    }

    @Override
    public void display(AuthenticationResult authenticationResult) {
        authenticationResult
                .error(() -> setMessageAndType(failedMessage, "danger"))
                .logout(() -> setMessageAndType(logoutMessage, "info"));
    }
}
