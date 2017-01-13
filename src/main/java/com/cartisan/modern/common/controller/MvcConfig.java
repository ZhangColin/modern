package com.cartisan.modern.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    MessageSource messageSource;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor());
        registry.addInterceptor(new LayoutNavigationInterceptor());
        registry.addInterceptor(new ErrorMessageInterceptor(messageSource));
        super.addInterceptors(registry);
    }
}
