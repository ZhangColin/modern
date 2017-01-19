package com.cartisan.modern.transaction.view;

import com.cartisan.modern.transaction.domain.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
@PropertySource("classpath:resultMessages.properties")
public class PresentableAddTransaction {
    @Value("${label.type}")
    String type;

    @Value("${label.description}")
    String description;

    @Value("${label.date}")
    String date;

    @Value("${label.amount}")
    String amount;

    @Value("${label.add}")
    String add;

    public void display(Model model, Transaction.Type[] values) {
        model.addAttribute("types", values);
        model.addAttribute("label.type", type);
        model.addAttribute("label.description", description);
        model.addAttribute("label.date", date);
        model.addAttribute("label.amount", amount);
        model.addAttribute("label.add", add);
    }
}
