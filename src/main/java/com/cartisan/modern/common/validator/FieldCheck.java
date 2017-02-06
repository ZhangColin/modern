package com.cartisan.modern.common.validator;

@FunctionalInterface
public interface FieldCheck<T> {
    boolean isValueUnique(T t);
}
