package com.cartisan.modern.transaction.view;

import com.cartisan.modern.transaction.domain.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static com.cartisan.modern.common.Formats.DAY;
import static java.time.format.DateTimeFormatter.ofPattern;

@Setter
@Getter
public class PresentableTransaction {
    private Transaction.Type type;
    private String description;
    private LocalDate date;
    private int amount;

    public String dateForView() {
        return date.format(ofPattern(DAY));
    }

    public String[] allViewText() {
        return new String[]{type.name(), description, dateForView(), String.valueOf(amount)};
    }
}
