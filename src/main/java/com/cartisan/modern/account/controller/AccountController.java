package com.cartisan.modern.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.cartisan.modern.common.controller.Urls.ACCOUNT;
import static com.cartisan.modern.common.controller.Urls.ACCOUNT_ADD;
import static com.cartisan.modern.common.controller.Urls.ADD;

@Controller
@RequestMapping(ACCOUNT)
public class AccountController {
    @GetMapping(ADD)
    public String addAccount() {
        return ACCOUNT_ADD;
    }
}
