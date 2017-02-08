package com.cartisan.modern.common.config;

import com.cartisan.modern.common.formatter.MonthFormatAnnotationFormatterFactory;
import com.cartisan.modern.common.interceptor.ErrorMessageInterceptor;
import com.cartisan.modern.common.interceptor.ExposedResourceBundleMessageSource;
import com.cartisan.modern.common.interceptor.LabelTextInterceptor;
import com.cartisan.modern.common.interceptor.LayoutNavigationInterceptor;
import com.cartisan.modern.common.view.ErrorMessage;
import com.cartisan.modern.user.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    ErrorMessage errorMessage;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor());
        registry.addInterceptor(new LayoutNavigationInterceptor());
        registry.addInterceptor(new ErrorMessageInterceptor(errorMessage));
        registry.addInterceptor(new LabelTextInterceptor(new ExposedResourceBundleMessageSource()));
        super.addInterceptors(registry);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
        registry.addFormatterForFieldAnnotation(new MonthFormatAnnotationFormatterFactory());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
