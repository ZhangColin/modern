package com.cartisan.modern.account.controller;

import com.cartisan.modern.account.domain.Account;
import com.cartisan.modern.account.domain.Accounts;
import com.cartisan.modern.account.view.PresentableAddAccount;
import com.cartisan.modern.common.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static com.cartisan.modern.common.controller.Urls.*;

@Controller
@RequestMapping(ACCOUNTS)
public class AccountAddController {
    private final Accounts accounts;
    private final View<String> message;
    private final PresentableAddAccount presentableAddAccount;

    @Value("${accounts.add.success}")
    String successMessage;

    @Value("${accounts.add.failed}")
    String failedMessage;

    @Autowired
    public AccountAddController(Accounts accounts, View<String> message, PresentableAddAccount presentableAddAccount) {
        this.accounts = accounts;
        this.message = message;
        this.presentableAddAccount = presentableAddAccount;
    }

    @GetMapping(ADD)
    public ModelAndView addAccount() {
        return presentableAddAccount;
    }

    @PostMapping(ADD)
    public ModelAndView submitAddAccount(@Valid @ModelAttribute Account account, BindingResult bindingResult) {
        if (!bindingResult.hasErrors())
            accounts.add(account)
                    .success(() -> message.display(successMessage))
                    .failed(() -> message.display(failedMessage));

        return addAccount();
    }
}
