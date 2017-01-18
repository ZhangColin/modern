package com.cartisan.modern.common.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.cartisan.modern.common.controller.Urls.MONTHLYBUDGET_ADD;
import static com.cartisan.modern.common.controller.Urls.TRANSACTION_LIST;

public class LayoutNavigationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("monthlyBudgetAddUrl", MONTHLYBUDGET_ADD);
        modelAndView.addObject("transactionUrl", TRANSACTION_LIST);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
