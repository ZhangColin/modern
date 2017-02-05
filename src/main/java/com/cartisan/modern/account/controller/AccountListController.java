package com.cartisan.modern.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.cartisan.modern.common.controller.Urls.ACCOUNTS;
import static com.cartisan.modern.common.controller.Urls.ACCOUNTS_INDEX;

@Controller
@RequestMapping(ACCOUNTS)
public class AccountListController {
    @GetMapping
    public String index() {
        return ACCOUNTS_INDEX;
    }
}
