package com.cartisan.modern.transaction.view;

import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static com.cartisan.modern.common.BeanUtils.copyProperties;
import static com.cartisan.modern.common.view.Messages.RESULT_MESSAGES;

@Component
@PropertySource(RESULT_MESSAGES)
public class PresentableTransactions extends ArrayList<PresentableTransaction> {
    private final Transactions transactions;

    @Value("${transaction.list.empty}")
    String noTransactionMessage;

    @Autowired
    public PresentableTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    public void add(Transaction transaction) {
        PresentableTransaction presentableTransaction = new PresentableTransaction();
        copyProperties(presentableTransaction, transaction);
        add(presentableTransaction);
    }

    public String hidden() {
        return isEmpty() ? "hidden" : "";
    }

    public String message() {
        return isEmpty() ? noTransactionMessage : "";
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

    public void display(Model model){
        transactions.processAll(this::add);
        model.addAttribute("transactions", this);
    }
}
