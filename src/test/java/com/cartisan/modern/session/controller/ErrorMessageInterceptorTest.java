package com.cartisan.modern.session.controller;

import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ErrorMessageInterceptorTest {
    Model mockModel = mock(Model.class);
    BindingResult stubBindingResult = mock(BindingResult.class);
    HttpServletRequest notUsedRequest = mock(HttpServletRequest.class);
    HttpServletResponse notUsedResponse = mock(HttpServletResponse.class);
    Object notUsedHandler = new Object();
    ModelAndView stubModelAndView = mock(ModelAndView.class);
    ErrorMessageInterceptor interceptor = new ErrorMessageInterceptor();

//    @Test
//    public void will_show_error_message_when_has_on_field_error() throws Exception {
//        givenFieldErrors(new FieldError("notUsedObjectName", "field", "error message"));
//
//        interceptor.postHandle(notUsedRequest, notUsedResponse, notUsedHandler, stubModelAndView);
//
//        verify(mockModel).addAttribute("error.field", "error message");
//    }
//
//    @Test
//    public void will_show_error_message_when_has_two_field_errors() throws Exception {
//        givenFieldErrors(
//                new FieldError("notUsedObjectName1", "field1", "error message"),
//                new FieldError("notUsedObjectName2", "field2", "another error message")
//        );
//
//        interceptor.postHandle(notUsedRequest, notUsedResponse, notUsedHandler, stubModelAndView);
//
//        verify(mockModel).addAttribute("error.field1", "error message");
//        verify(mockModel).addAttribute("error.field2", "another error message");
//    }

    private void givenFieldErrors(FieldError... errors) {
        HashMap<String, Object> modelMap = new HashMap<>();
        modelMap.put(BindingResult.MODEL_KEY_PREFIX + "monthlyBudget", stubBindingResult);
        when(stubModelAndView.getModel()).thenReturn(modelMap);
        when(stubBindingResult.getFieldErrors()).thenReturn(asList(errors));
    }
}
