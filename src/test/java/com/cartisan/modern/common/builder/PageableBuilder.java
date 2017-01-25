package com.cartisan.modern.common.builder;

import com.cartisan.modern.common.controller.Pageable;
import com.cartisan.modern.common.controller.PageableFactory;

public class PageableBuilder {
    public static PageableBuilder defaultResultRange(){
        return new PageableBuilder();
    }

    public Pageable build(){
        return new PageableFactory().create(1);
    }
}
