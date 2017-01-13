package com.cartisan.modern.transaction.view;

import com.cartisan.modern.transaction.domain.Transaction;
import lombok.Setter;

@Setter
public class PresentableTransaction {
    private Transaction.Type type;
    private String description;
    private String date;
    private int amount;
}
