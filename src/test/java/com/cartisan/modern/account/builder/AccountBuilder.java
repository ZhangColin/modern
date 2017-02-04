package com.cartisan.modern.account.builder;

import com.cartisan.modern.account.domain.Account;

public class AccountBuilder {
    public static Account.AccountBuilder defaultAccount(){
        return Account.builder().name("name").balanceBroughtForward(0);
    }
}
