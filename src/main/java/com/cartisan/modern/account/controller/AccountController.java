package com.cartisan.modern.account.controller;

import com.cartisan.modern.account.domain.Account;
import com.cartisan.modern.account.domain.Accounts;
import com.cartisan.modern.common.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.cartisan.modern.common.controller.Urls.ACCOUNT;
import static com.cartisan.modern.common.controller.Urls.ACCOUNT_ADD;
import static com.cartisan.modern.common.controller.Urls.ADD;

@Controller
@RequestMapping(ACCOUNT)
public class AccountController {
    private final Accounts accounts;
    private final View<String> message;

    @Value("${account.add.success}")
    String successMessage;

    @Autowired
    public AccountController(Accounts accounts, View<String> message) {
        this.accounts = accounts;
        this.message = message;
    }

    @GetMapping(ADD)
    public String addAccount() {
        return ACCOUNT_ADD;
    }

    @PostMapping(ADD)
    public String submitAddAccount(@ModelAttribute Account account) {
        accounts.add(account);
        message.display(successMessage);
        return ACCOUNT_ADD;
    }
}
