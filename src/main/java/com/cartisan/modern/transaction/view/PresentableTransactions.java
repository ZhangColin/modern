package com.cartisan.modern.transaction.view;

import com.cartisan.modern.common.view.CombinableModelAndView;
import com.cartisan.modern.common.view.View;
import com.cartisan.modern.transaction.domain.Transaction;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.cartisan.modern.common.BeanUtils.copyProperties;
import static com.cartisan.modern.common.controller.Urls.TRANSACTIONS_INDEX;
import static com.cartisan.modern.common.view.MessageSources.RESULT_MESSAGES_FULL_NAME;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
@PropertySource(RESULT_MESSAGES_FULL_NAME)
public class PresentableTransactions extends ModelAndView implements View<Transaction>, CombinableModelAndView {
    private final List<PresentableTransaction> presentableTransactions = new ArrayList<>();

    @Builder
    public PresentableTransactions(@Value("${transactions.list.empty}") String noTransactionMessage) {
        addObject("transactions", presentableTransactions);
        showMessage(noTransactionMessage);
        setViewName(TRANSACTIONS_INDEX);
    }

    private void showMessage(String noTransactionMessage) {
        addObject("message", noTransactionMessage);
    }

    private PresentableTransaction presentableTransactionFrom(Transaction transaction) {
        PresentableTransaction presentableTransaction = new PresentableTransaction();
        copyProperties(presentableTransaction, transaction);
        return presentableTransaction;
    }

    @Override
    public void display(Transaction transaction) {
        presentableTransactions.add(presentableTransactionFrom(transaction));
        hideMessage();
    }

    private void hideMessage() {
        getModelMap().remove("message");
    }

    @Override
    public ModelAndView original() {
        return this;
    }
}
