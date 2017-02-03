package com.cartisan.modern.account.domain;

import com.cartisan.modern.account.repository.AccountRepository;
import com.cartisan.modern.common.callback.PostActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Accounts {
    private final AccountRepository accountRepository;

    @Autowired
    public Accounts(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public PostActions add(Account account) {
        accountRepository.save(account);
        return null;
    }
}
