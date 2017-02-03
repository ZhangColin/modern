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

    @Test
    public void left_panel_menu_urls() throws Exception {
        LayoutNavigationInterceptor interceptor = new LayoutNavigationInterceptor();

        ModelAndView modelAndView = new ModelAndView();
        interceptor.postHandle(anyRequest, anyResponse, anyHandler, modelAndView);

        assertThat(modelAndView.getModelMap().get("monthlyBudgetsAddUrl")).isEqualTo(MONTHLYBUDGETS_ADD);
        assertThat(modelAndView.getModelMap().get("transactionsUrl")).isEqualTo(TRANSACTIONS);
        assertThat(modelAndView.getModelMap().get("signoutUrl")).isEqualTo(SIGNOUT);
    }
}
