package com.cartisan.modern.common.interceptor;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.cartisan.modern.common.controller.Urls.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class LayoutNavigationInterceptorTest {

    private final HttpServletRequest anyRequest = mock(HttpServletRequest.class);
    private final HttpServletResponse anyResponse = mock(HttpServletResponse.class);
    private final Object anyHandler = new Object();
    private final ModelAndView modelAndView = new ModelAndView();

    @Test
    public void left_panel_menu_urls() throws Exception {
        postHandle();

        assertThat(modelAndViewAttribute("monthlyBudgetsUrl")).isEqualTo(MONTHLYBUDGETS_ADD);
        assertThat(modelAndViewAttribute("transactionsUrl")).isEqualTo(TRANSACTIONS);
        assertThat(modelAndViewAttribute("accountsUrl")).isEqualTo(ACCOUNTS);
        assertThat(modelAndViewAttribute("signoutUrl")).isEqualTo(SIGNOUT);
        assertThat(modelAndViewAttribute("rootUrl")).isEqualTo(ROOT);
    }

    private Object modelAndViewAttribute(String monthlyBudgetsAddUrl) {
        return modelAndView.getModelMap().get(monthlyBudgetsAddUrl);
    }

    private void postHandle() throws Exception {
        new LayoutNavigationInterceptor().postHandle(anyRequest, anyResponse, anyHandler, modelAndView);
    }
}
