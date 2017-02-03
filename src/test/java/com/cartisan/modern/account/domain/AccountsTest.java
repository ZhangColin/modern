package com.cartisan.modern.account.domain;

import com.cartisan.modern.account.repository.AccountRepository;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AccountsTest {
    @Test
    public void should_save_account() {
        AccountRepository mockAccountRepository = mock(AccountRepository.class);
        Accounts accounts = new Accounts(mockAccountRepository);

        Account account = new Account();
        accounts.add(account);

        verify(mockAccountRepository).save(account);
    }
}