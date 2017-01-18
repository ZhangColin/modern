package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.Transactions;
import com.cartisan.modern.transaction.view.PresentableTransactions;
import com.cartisan.modern.transaction.view.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.cartisan.modern.common.controller.ControllerHelper.thenSetMessage;
import static com.cartisan.modern.common.controller.Urls.TRANSACTION_ADD;
import static com.cartisan.modern.common.controller.Urls.TRANSACTION_LIST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@PropertySource("classpath:resultMessages.properties")
public class TransactionController {
    private final Transactions transactions;

    @Value("${transaction.add.success}")
    String successMessage;

    @Value("${transaction.add.failed}")
    String failedMessage;

    @Value("${transaction.list.empty}")
    String noTransactionMessage;

    @Autowired
    public TransactionController(Transactions transactions) {
        this.transactions = transactions;
    }

    @RequestMapping(value = TRANSACTION_ADD, method = POST)
    public String submitAddTransaction(@Valid @ModelAttribute Transaction transaction, BindingResult result, Model model) {
        if (!result.hasFieldErrors())
            transactions.add(transaction)
                    .success(thenSetMessage(model, successMessage))
                    .failed(thenSetMessage(model, failedMessage));
        return addTransaction(model);
    }

    @RequestMapping(value = TRANSACTION_ADD, method = GET)
    public String addTransaction(Model model) {
        new Types(model, Transaction.Type.values());
        return TRANSACTION_ADD;
    }

    @RequestMapping(value = TRANSACTION_LIST, method = GET)
    public String showTransactions(Model model) {
        new PresentableTransactions(model, noTransactionMessage, transactions);

        return TRANSACTION_LIST;
    }
}
