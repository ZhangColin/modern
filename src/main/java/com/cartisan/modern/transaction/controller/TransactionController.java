package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.common.view.Message;
import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.Transactions;
import com.cartisan.modern.transaction.view.PresentableAddTransaction;
import com.cartisan.modern.transaction.view.PresentableTransactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.cartisan.modern.common.controller.Urls.*;
import static com.cartisan.modern.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;

@Controller
@PropertySource(RESULT_MESSAGES_FULL_NAME)
@RequestMapping(TRANSACTION)
public class TransactionController {
    private final Transactions transactions;
    private final PresentableAddTransaction presentableAddTransaction;
    private final PresentableTransactions presentableTransactions;
    private final Message message;

    @Value("${transaction.add.success}")
    String successMessage;

    @Value("${transaction.add.failed}")
    String failedMessage;

    @Autowired
    public TransactionController(Transactions transactions,
                                 PresentableAddTransaction presentableAddTransaction,
                                 PresentableTransactions presentableTransactions,
                                 Message message) {
        this.transactions = transactions;
        this.presentableAddTransaction = presentableAddTransaction;
        this.presentableTransactions = presentableTransactions;
        this.message = message;
    }

    @PostMapping(ADD)
    public String submitAddTransaction(
            @Valid @ModelAttribute Transaction transaction, BindingResult result) {
        if (!result.hasFieldErrors())
            transactions.add(transaction)
                    .success(()->message.display(successMessage))
                    .failed(()->message.display(failedMessage));
        return addTransaction();
    }

    @GetMapping(ADD)
    public String addTransaction() {
        presentableAddTransaction.display();
        return TRANSACTION_ADD;
    }

    @GetMapping
    public String index() {
        presentableTransactions.display();

        return TRANSACTION_INDEX;
    }
}
