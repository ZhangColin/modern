package com.cartisan.modern.acceptancetest.steps;

import lombok.Data;

@Data
public class Transaction {
    private String type;
    private String description;
    private String date;
    private String amount;
}
