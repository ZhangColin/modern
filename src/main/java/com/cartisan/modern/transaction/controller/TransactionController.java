package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.common.view.View;
import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.Transactions;
import com.cartisan.modern.transaction.view.PresentableAddTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static com.cartisan.modern.common.controller.Urls.ADD;
import static com.cartisan.modern.common.controller.Urls.TRANSACTION;
import static com.cartisan.modern.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;

@Controller
@PropertySource(RESULT_MESSAGES_FULL_NAME)
@RequestMapping(TRANSACTION)
public class TransactionController {
    private final Transactions transactions;
    private final PresentableAddTransaction presentableAddTransaction;
    private final View<String> message;

    @Value("${transaction.add.success}")
    String successMessage;

    @Value("${transaction.add.failed}")
    String failedMessage;

    @Autowired
    public TransactionController(Transactions transactions,
                                 PresentableAddTransaction presentableAddTransaction,
                                 View<String> message) {
        this.transactions = transactions;
        this.presentableAddTransaction = presentableAddTransaction;
        this.message = message;
    }

    @PostMapping(ADD)
    public ModelAndView submitAddTransaction(
            @Valid @ModelAttribute Transaction transaction, BindingResult result) {
        if (!result.hasFieldErrors())
            transactions.add(transaction)
                    .success(()->message.display(successMessage))
                    .failed(()->message.display(failedMessage));
        return addTransaction();
    }

    @GetMapping(ADD)
    public ModelAndView addTransaction() {
        return presentableAddTransaction;
    }
}
