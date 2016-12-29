package com.cartisan.modern.transaction.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
public class Transaction {
    private Type type;
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Integer amount;

    public enum Type {
        Income
    }
}
