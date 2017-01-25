package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.common.controller.PageableFactory;
import com.cartisan.modern.transaction.domain.Transactions;
import com.cartisan.modern.transaction.view.PresentableSummaryOfTransactions;
import com.cartisan.modern.transaction.view.PresentableTransactions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static com.cartisan.modern.common.controller.PageableFactory.DEFAULT_PAGE_NUMBER;
import static com.cartisan.modern.common.controller.Urls.TRANSACTION;
import static com.cartisan.modern.common.view.ModelAndViewCombiner.combine;

@Controller
@RequestMapping(TRANSACTION)
public class TransactionListController {
    private final Transactions transactions;
    private final PresentableTransactions presentableTransactions;
    private final PresentableSummaryOfTransactions presentableSummaryOfTransactions;
    private final PageableFactory pageableFactory;

    public TransactionListController(
            Transactions transactions,
            PresentableTransactions presentableTransactions,
            PresentableSummaryOfTransactions presentableSummaryOfTransactions,
            PageableFactory pageableFactory) {
        this.transactions = transactions;
        this.presentableTransactions = presentableTransactions;
        this.presentableSummaryOfTransactions = presentableSummaryOfTransactions;
        this.pageableFactory = pageableFactory;
    }

    @GetMapping
    public ModelAndView index(@RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) int page) {
        transactions.processAll(presentableTransactions::display, pageableFactory.create(page))
                .withSummary(presentableSummaryOfTransactions::display);
        return combine(presentableTransactions).with(presentableSummaryOfTransactions);
    }
}
