package com.cartisan.modern.account.domain;

import com.cartisan.modern.account.repository.AccountRepository;
import org.junit.Test;

import static com.cartisan.modern.common.callback.PostActionsFactory.failed;
import static com.cartisan.modern.common.callback.PostActionsFactory.success;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AccountsTest {
    AccountRepository mockAccountRepository = mock(AccountRepository.class);
    Accounts accounts = new Accounts(mockAccountRepository);

    @Test
    public void should_save_account() {
        Account account = new Account();
        accounts.add(account);

        verify(mockAccountRepository).save(account);
    }

    @Test
    public void should_return_success_when_save_with_no_exception(){
        assertThat(accounts.add(new Account())).isInstanceOf(success().getClass());
    }

    @Test
    public void should_return_failed_when_save_with_exception(){
        given_account_repository_save_will_failed();

        assertThat(accounts.add(new Account())).isInstanceOf(failed().getClass());
    }

    private void given_account_repository_save_will_failed() {
        doThrow(IllegalArgumentException.class).when(mockAccountRepository).save(any(Account.class));
    }
}