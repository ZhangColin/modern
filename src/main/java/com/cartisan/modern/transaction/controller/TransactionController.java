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
import java.util.ArrayList;
import java.util.List;

import static com.cartisan.modern.common.BeanUtils.copyProperties;
import static com.cartisan.modern.common.controller.ControllerHelper.setMessage;
import static com.cartisan.modern.common.controller.ControllerHelper.thenSetMessage;
import static com.cartisan.modern.common.controller.Urls.TRANSACTION_ADD;
import static com.cartisan.modern.common.controller.Urls.TRANSACTION_LIST;

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

    @RequestMapping(value = TRANSACTION_ADD, method = RequestMethod.POST)
    public String submitAddTransaction(@Valid @ModelAttribute Transaction transaction, BindingResult result, Model model) {
        if (!result.hasFieldErrors())
            transactions.add(transaction)
                    .success(thenSetMessage(model, successMessage))
                    .failed(thenSetMessage(model, failedMessage));
        return addTransaction(model);
    }

    @RequestMapping(value = TRANSACTION_ADD, method = RequestMethod.GET)
    public String addTransaction(Model model) {
        model.addAttribute("types", Transaction.Type.values());
        return TRANSACTION_ADD;
    }

    @RequestMapping(value = TRANSACTION_LIST, method = RequestMethod.GET)
    public String showTransactions(Model model) {
        List<PresentableTransaction> all = new ArrayList<>();
        transactions.processAll(transaction -> all.add(presentableTransaction(transaction)));

        if (all.isEmpty()) {
            setMessage(model, noTransactionMessage);
            model.addAttribute("table.hidden", "hidden");
        } else {
            model.addAttribute("transactions", all);
        }

        return TRANSACTION_LIST;
    }

    private PresentableTransaction presentableTransaction(Transaction transaction) {
        PresentableTransaction presentableTransaction = new PresentableTransaction();
        copyProperties(presentableTransaction, transaction);

        return presentableTransaction;
    }
}
