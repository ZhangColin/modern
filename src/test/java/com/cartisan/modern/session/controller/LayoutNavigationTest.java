package com.cartisan.modern.session.controller;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.cartisan.modern.Urls.MONTHLYBUDGET_ADD;
import static com.cartisan.modern.Urls.TRANSACTION_ADD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class LayoutNavigationTest {

    private final HttpServletRequest anyRequest = mock(HttpServletRequest.class);
    private final HttpServletResponse anyResponse = mock(HttpServletResponse.class);
    private final Object anyHandler = new Object();

    @Test
    public void left_panel_menu_urls() throws Exception {
        LayoutNavigationInterceptor interceptor = new LayoutNavigationInterceptor();

        ModelAndView modelAndView = new ModelAndView();
        interceptor.postHandle(anyRequest, anyResponse, anyHandler, modelAndView);

        assertThat(modelAndView.getModelMap().get("monthlyBudgetAddUrl")).isEqualTo(MONTHLYBUDGET_ADD);
        assertThat(modelAndView.getModelMap().get("transactionAddUrl")).isEqualTo(TRANSACTION_ADD);
    }
}
