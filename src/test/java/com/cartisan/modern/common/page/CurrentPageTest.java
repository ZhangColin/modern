package com.cartisan.modern.common.page;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrentPageTest {
    private static final int FIRST_PAGE = 0;
    HttpServletRequest mockRequest = mock(HttpServletRequest.class);
    CurrentPage currentPage = new CurrentPage(mockRequest);

    @Test
    public void no_page_param() {
        assertThat(currentPage.number()).isEqualTo(FIRST_PAGE);
    }

    @Test
    public void valid_page() {
        given_page_param_is("1");

        assertThat(currentPage.number()).isEqualTo(1);
    }

    @Test
    public void not_a_number() {
        given_page_param_is("NotANumber");

        assertThat(currentPage.number()).isEqualTo(FIRST_PAGE);
    }

    @Test
    public void a_negative_number() {
        given_page_param_is("-1");

        assertThat(currentPage.number()).isEqualTo(FIRST_PAGE);
    }

    private void given_page_param_is(String value) {
        when(mockRequest.getParameter("page")).thenReturn(value);
    }
}
