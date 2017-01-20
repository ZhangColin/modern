package com.cartisan.modern.transaction.view;

import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static com.cartisan.modern.common.BeanUtils.copyProperties;
import static com.cartisan.modern.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class PresentableTransactions extends ArrayList<PresentableTransaction> {
    private final Transactions transactions;
    private final HttpServletRequest request;

    @Value("${transaction.list.empty}")
    String noTransactionMessage;

    @Autowired
    public PresentableTransactions(Transactions transactions, HttpServletRequest request) {
        this.transactions = transactions;
        this.request = request;
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

    public void display(){
        transactions.processAll(this::add);
        request.setAttribute("transactions", this);
    }
}
