package com.cartisan.modern.common.view;

@FunctionalInterface
public interface View<T> {
    void display(T domainObject);
}
