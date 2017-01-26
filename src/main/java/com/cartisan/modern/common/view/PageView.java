package com.cartisan.modern.common.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.cartisan.modern.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class PageView extends ModelAndView {
    public static final String PAGE_PARAM_NAME = "page";

    public PageView(
            @Autowired HttpServletRequest request,
            @Value("${pagination.currentPage}") String currentPageMessage) {
        displayCurrentPage(currentPage(request), currentPageMessage);
        displayPreviousPage(currentPage(request));
    }

    private void displayPreviousPage(int currentPage) {
        if (isOnFirstPage(currentPage)){
            displayPreviousPageText();
        }
        else{
            displayPreviousPageLink(currentPage);
        }
    }

    private void displayPreviousPageLink(int currentPage) {
        addObject("previousPageUrl", previousPageUrl(currentPage));
        addObject("previousPageTextHidden", "hidden");
        addObject("previousPageUrlHidden", "");
    }

    private String previousPageUrl(int currentPage) {
        Params page = new Params();
        page.add(PAGE_PARAM_NAME, valueOf(currentPage-1));
        return page.getQuery();
    }

    private void displayPreviousPageText() {
        addObject("previousPageUrl", "#");
        addObject("previousPageTextHidden", "");
        addObject("previousPageUrlHidden", "hidden");
    }

    private boolean isOnFirstPage(int currentPage) {
        return currentPage==0;
    }

    private void displayCurrentPage(int currentPage, String currentPageMessage) {
        addObject("currentPage", format(currentPageMessage, currentPage));
    }

    private int currentPage(HttpServletRequest request) {
        if (request.getParameter("page")==null)
            return 0;
        else
            return parseInt(request.getParameter("page"));
    }
}
