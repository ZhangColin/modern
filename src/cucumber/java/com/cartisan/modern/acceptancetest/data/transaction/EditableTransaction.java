package com.cartisan.modern.acceptancetest.data.transaction;

import lombok.Data;

@Data
public class EditableTransaction {
    private String type;
    private String description;
    private String date;
    private String amount;
}
