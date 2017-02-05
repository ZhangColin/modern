package com.cartisan.modern.account.domain;

import com.cartisan.modern.account.repository.AccountRepository;
import com.cartisan.modern.common.callback.FailedPostActions;
import com.cartisan.modern.common.callback.PostActions;
import com.cartisan.modern.common.callback.SuccessPostActions;
import com.cartisan.modern.common.validator.FieldCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Accounts implements FieldCheck<String> {
    private final AccountRepository accountRepository;

    @Autowired
    public Accounts(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public PostActions add(Account account) {
        try {
            accountRepository.save(account);
            return new SuccessPostActions();
        }
        catch (IllegalArgumentException e){
            return new FailedPostActions();
        }
    }

    @Override
    public boolean isValueUnique(String value) {
        return !accountRepository.existsByName(value);
    }
}
