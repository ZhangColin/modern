package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.Urls;
import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.cartisan.modern.Urls.TRANSACTION_ADD;

@Controller
public class TransactionController {
    private final Transactions transactions;

    @Autowired
    public TransactionController(Transactions transactions) {
        this.transactions = transactions;
    }

    @RequestMapping(value = TRANSACTION_ADD, method = RequestMethod.POST)
    public String submitAddTransaction(@ModelAttribute Transaction transaction, Model model) {
        transactions.add(transaction)
                .success(setMessage(model, "Successfully add transaction"))
                .failed(setMessage(model, "Add transaction failed"));
        return TRANSACTION_ADD;
    }

    @RequestMapping(value = TRANSACTION_ADD, method = RequestMethod.GET)
    public String addTransaction() {
        return TRANSACTION_ADD;
    }

    private Runnable setMessage(Model model, String message) {
        return () -> model.addAttribute("message", message);
    }
}
