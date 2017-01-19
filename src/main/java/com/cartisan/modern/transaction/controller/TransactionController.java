package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.Transactions;
import com.cartisan.modern.transaction.view.PresentableAddTransaction;
import com.cartisan.modern.transaction.view.PresentableTransactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.cartisan.modern.common.controller.ControllerHelper.thenSetMessage;
import static com.cartisan.modern.common.controller.Urls.*;
import static com.cartisan.modern.common.view.Messages.RESULT_MESSAGES;

@Controller
@PropertySource(RESULT_MESSAGES)
@RequestMapping(TRANSACTION)
public class TransactionController {
    private final Transactions transactions;
    private final PresentableAddTransaction presentableAddTransaction;
    private final PresentableTransactions presentableTransactions;

    @Value("${transaction.add.success}")
    String successMessage;

    @Value("${transaction.add.failed}")
    String failedMessage;

    @Autowired
    public TransactionController(Transactions transactions, PresentableAddTransaction presentableAddTransaction, PresentableTransactions presentableTransactions) {
        this.transactions = transactions;
        this.presentableAddTransaction = presentableAddTransaction;
        this.presentableTransactions = presentableTransactions;
    }

    @PostMapping(ADD)
    public String submitAddTransaction(@Valid @ModelAttribute Transaction transaction, BindingResult result, Model model) {
        if (!result.hasFieldErrors())
            transactions.add(transaction)
                    .success(thenSetMessage(model, successMessage))
                    .failed(thenSetMessage(model, failedMessage));
        return addTransaction(model);
    }

    @GetMapping(ADD)
    public String addTransaction(Model model) {
        presentableAddTransaction.display(model, Transaction.Type.values());
        return TRANSACTION_ADD;
    }

    @GetMapping
    public String index(Model model) {
        presentableTransactions.display(model);

        return TRANSACTION_INDEX;
    }
}
