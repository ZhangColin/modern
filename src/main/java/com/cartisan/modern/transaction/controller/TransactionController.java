package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.Transactions;
import com.cartisan.modern.transaction.view.PresentableTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import static com.cartisan.modern.common.controller.ControllerHelper.setMessage;
import static com.cartisan.modern.common.controller.Urls.TRANSACTION_ADD;
import static com.cartisan.modern.common.controller.Urls.TRANSACTION_LIST;
import static java.util.Arrays.asList;

@Controller
@PropertySource("classpath:resultMessages.properties")
public class TransactionController {
    private final Transactions transactions;

    @Value("${transaction.add.success}")
    String successMessage;

    @Value("${transaction.add.failed}")
    String failedMessage;

    @Autowired
    public TransactionController(Transactions transactions) {
        this.transactions = transactions;
    }

    @RequestMapping(value = TRANSACTION_ADD, method = RequestMethod.POST)
    public String submitAddTransaction(@Valid @ModelAttribute Transaction transaction, BindingResult result, Model model) {
        if (!result.hasFieldErrors())
            transactions.add(transaction)
                    .success(setMessage(model, successMessage))
                    .failed(setMessage(model, failedMessage));
        return addTransaction(model);
    }

    @RequestMapping(value = TRANSACTION_ADD, method = RequestMethod.GET)
    public String addTransaction(Model model) {
        model.addAttribute("types", Transaction.Type.values());
        return TRANSACTION_ADD;
    }

    @RequestMapping(value = TRANSACTION_LIST, method = RequestMethod.GET)
    public String showTransactions(Model model) {
        model.addAttribute("transactions", asList(
                presentableTransaction(Transaction.Type.Income, "Course Registration", "2016-08-14", 4000),
                presentableTransaction(Transaction.Type.Outcome, "Buy MacBook Pro", "2015-11-01", 100)
        ));
        return TRANSACTION_LIST;
    }

    private PresentableTransaction presentableTransaction(Transaction.Type type, String description, String date, int amount) {
        PresentableTransaction presentableTransaction = new PresentableTransaction();
        presentableTransaction.setType(type);
        presentableTransaction.setDescription(description);
        presentableTransaction.setDate(date);
        presentableTransaction.setAmount(amount);

        return presentableTransaction;
    }
}
