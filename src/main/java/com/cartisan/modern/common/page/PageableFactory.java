package com.cartisan.modern.common.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
public class PageableFactory {
    private final int perPageLimit;
    private final CurrentPage currentPage;
    public static final String PER_PAGE_LIMIT_PROPERTY_NAME = "application.perPageLimit";


    public PageableFactory(@Value("${" + PER_PAGE_LIMIT_PROPERTY_NAME + "}") int perPageLimit,
                           @Autowired CurrentPage currentPage) {
        this.currentPage = currentPage;
        this.perPageLimit = perPageLimit;
    }

    public Pageable create() {
        return new PageRequest(currentPage.number()-1, perPageLimit);
    }

}
