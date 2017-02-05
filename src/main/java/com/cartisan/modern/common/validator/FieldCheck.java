package com.cartisan.modern.common.validator;

public interface FieldCheck<T> {
    boolean isValueUnique(T t);
}
