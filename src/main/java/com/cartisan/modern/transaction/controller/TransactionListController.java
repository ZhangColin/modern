package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.common.page.PageView;
import com.cartisan.modern.common.page.PageableFactory;
import com.cartisan.modern.transaction.domain.Transactions;
import com.cartisan.modern.transaction.view.PresentableSummaryOfTransactions;
import com.cartisan.modern.transaction.view.PresentableTransactions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.cartisan.modern.common.controller.Urls.TRANSACTION;
import static com.cartisan.modern.common.view.ModelAndViewCombiner.combine;

@Controller
@RequestMapping(TRANSACTION)
public class TransactionListController {
    private final Transactions transactions;
    private final PresentableTransactions presentableTransactions;
    private final PresentableSummaryOfTransactions presentableSummaryOfTransactions;
    private final PageableFactory pageableFactory;
    private final PageView pageView;

    public TransactionListController(
            Transactions transactions,
            PresentableTransactions presentableTransactions,
            PresentableSummaryOfTransactions presentableSummaryOfTransactions,
            PageableFactory pageableFactory,
            PageView pageView) {
        this.transactions = transactions;
        this.presentableTransactions = presentableTransactions;
        this.presentableSummaryOfTransactions = presentableSummaryOfTransactions;
        this.pageableFactory = pageableFactory;
        this.pageView = pageView;
    }

    @GetMapping
    public ModelAndView index() {
        transactions.processAll(presentableTransactions::display, pageableFactory.create())
                .withSummary(presentableSummaryOfTransactions::display)
                .withTotalPageCount(pageView::display);
        combine(presentableTransactions).with(presentableSummaryOfTransactions);
        combine(presentableTransactions).with(pageView);

        return presentableTransactions;
    }
}
