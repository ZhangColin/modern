package com.cartisan.modern.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.cartisan.modern.common.controller.Urls.*;

public class LayoutNavigationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView==null){
            return ;
        }
        modelAndView.addObject("monthlyBudgetsUrl", MONTHLYBUDGETS_ADD);
        modelAndView.addObject("transactionsUrl", TRANSACTIONS);
        modelAndView.addObject("accountsUrl", ACCOUNTS);
        modelAndView.addObject("signoutUrl", SIGNOUT);
        modelAndView.addObject("rootUrl", ROOT);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
