package com.cartisan.modern.account.domain;

import com.cartisan.modern.account.repository.AccountRepository;
import com.cartisan.modern.common.callback.PostActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.cartisan.modern.common.callback.PostActionsFactory.failed;
import static com.cartisan.modern.common.callback.PostActionsFactory.success;

@Service
public class Accounts {
    private final AccountRepository accountRepository;

    @Autowired
    public Accounts(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public PostActions add(Account account) {
        try {
            accountRepository.save(account);
            return success();
        }
        catch (IllegalArgumentException e){
            return failed();
        }
    }
}
