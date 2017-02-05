package com.cartisan.modern.account.domain;

import com.cartisan.modern.account.repository.AccountRepository;
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

    public AccountPostActions add(Account account) {
        try {
            accountRepository.save(account);
            return new SuccessAccountPostActions();
        }
        catch (IllegalArgumentException e){
            return new FailedAccountPostActions();
        }
    }

    @Override
    public boolean isValueUnique(String value) {
        return !accountRepository.existsByName(value);
    }
}
