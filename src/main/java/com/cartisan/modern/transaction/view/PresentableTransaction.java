package com.cartisan.modern.transaction.view;

import com.cartisan.modern.transaction.domain.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.cartisan.modern.common.Formats.DAY;

@Setter
@Getter
public class PresentableTransaction {
    private Transaction.Type type;
    private String description;
    private Date date;
    private int amount;

    public String dateForView(){
        return new SimpleDateFormat(DAY).format(date);
    }

    public String[] allViewText(){
        return new String[]{type.name(), description, dateForView(), String.valueOf(amount)};
    }
}
