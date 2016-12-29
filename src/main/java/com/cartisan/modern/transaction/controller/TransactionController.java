package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.transaction.domain.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TransactionController {
    @RequestMapping("/confirm_add_transaction")
    public String confirm(Transaction transaction){
        return "add_transaction";
    }
}
