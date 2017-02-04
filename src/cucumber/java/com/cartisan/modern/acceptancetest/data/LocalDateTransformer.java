package com.cartisan.modern.acceptancetest.data;

import cucumber.api.Transformer;

import java.time.LocalDate;

import static com.cartisan.modern.common.Formats.parseMonth;

public class LocalDateTransformer extends Transformer<LocalDate> {

    @Override
    public LocalDate transform(String value) {
        return parseMonth(value);
    }
}
