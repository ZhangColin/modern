package com.cartisan.modern.common.builder;

import com.cartisan.modern.common.page.CurrentPage;
import com.cartisan.modern.common.page.PageView;

import static com.cartisan.modern.common.page.CurrentPage.FIRST_PAGE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PageViewBuilder {
    private int pageNumber = FIRST_PAGE;
    private String currentPageMessage = "whatever message";

    public static PageViewBuilder builder(){
        return new PageViewBuilder();
    }

    public PageView build(){
        return new PageView(currentPageMessage, stubCurrentPage());
    }

    private CurrentPage stubCurrentPage() {
        CurrentPage stubCurrentPage = mock(CurrentPage.class);
        when(stubCurrentPage.number()).thenReturn(pageNumber);
        return stubCurrentPage;
    }

    public PageViewBuilder withCurrentPage(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public PageViewBuilder withCurrentPageMessage(String currentPageMessage) {
        this.currentPageMessage = currentPageMessage;
        return this;
    }
}
