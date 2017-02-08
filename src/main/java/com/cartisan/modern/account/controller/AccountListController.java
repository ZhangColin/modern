package com.cartisan.modern.account.controller;

import com.cartisan.modern.account.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

import static com.cartisan.modern.common.controller.Urls.ACCOUNTS;
import static com.cartisan.modern.common.controller.Urls.ACCOUNTS_INDEX;

@Controller
@RequestMapping(ACCOUNTS)
public class AccountListController {
    @GetMapping
    public String index() {
        return ACCOUNTS_INDEX;
    }

    @GetMapping("list.json")
    @ResponseBody
    public List<Account> list(){
        return Arrays.asList(new Account(0, "Jackson", 100));
    }
}
