package com.cartisan.modern.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;
import java.util.stream.Stream;

import static com.cartisan.modern.common.view.Messages.LABEL_TEXT_SHORT_NAME;

public class LabelTextInterceptor implements HandlerInterceptor {
    private static final String PREFIX = "label";
    private static final String VIEW_NAME_DELIMITER = "/";

    private final ExposedResourceBundleMessageSource exposedResourceBundleMessageSource;

    public LabelTextInterceptor(ExposedResourceBundleMessageSource exposedResourceBundleMessageSource) {
        this.exposedResourceBundleMessageSource = exposedResourceBundleMessageSource;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        allLabelMessageKeys(request).stream()
                .filter(key -> isLabelMessageKeyForView(key, modelAndView.getViewName()))
                .forEach(key -> addLabelMessage(key, modelAndView, request));
    }

    private ModelAndView addLabelMessage(String key, ModelAndView modelAndView, HttpServletRequest request) {
        return modelAndView.addObject(labelMessageCodeForView(key), exposedResourceBundleMessageSource.getMessageOverrided(key, null, request.getLocale()));
    }

    private String labelMessageCodeForView(String key) {
        return key.substring(key.indexOf(PREFIX));
    }

    private boolean isLabelMessageKeyForView(String key, String viewName) {
        return isAllNameInKey(key, viewName) || key.startsWith(PREFIX);
    }

    private boolean isAllNameInKey(String key, String viewName) {
        return Stream.of(viewName.split(VIEW_NAME_DELIMITER)).allMatch(name->key.contains(name));
    }

    private Set<String> allLabelMessageKeys(HttpServletRequest request) {
        return exposedResourceBundleMessageSource.getKeys(LABEL_TEXT_SHORT_NAME, request.getLocale());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
