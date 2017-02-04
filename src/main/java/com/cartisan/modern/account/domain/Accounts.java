package com.cartisan.modern.account.domain;

import com.cartisan.modern.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Accounts {
    private final AccountRepository accountRepository;

    @Autowired
    public Accounts(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountPostActions add(Account account) {
        try {
            if (accountRepository.existsByName(account.getName()))
                return new NameDuplicatedAccountPostActions();
            accountRepository.save(account);
            return new SuccessAccountPostActions();
        }
        catch (IllegalArgumentException e){
            return new FailedAccountPostActions();
        }
    }
}
