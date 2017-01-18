package com.cartisan.modern.transaction.view;

import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.Transactions;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static com.cartisan.modern.common.BeanUtils.copyProperties;

public class PresentableTransactions extends ArrayList<PresentableTransaction> {
    private final Model model;
    private final String message;

    public PresentableTransactions(Model model, String message, Transactions transactions) {
        this.model = model;
        this.message = message;
        transactions.processAll(this::add);
        this.model.addAttribute("transactions", this);
    }

    public void add(Transaction transaction) {
        PresentableTransaction presentableTransaction = new PresentableTransaction();
        copyProperties(presentableTransaction, transaction);
        add(presentableTransaction);
    }

    public String display() {
        return isEmpty() ? "hidden" : "";
    }

    public String message() {
        return isEmpty() ? message : "";
    }

    public int totalIncome(){
        return 14000;
    }

    public int totalOutcome() {
        return 30000;
    }

    public int balance() {
        return -16000;
    }
}
