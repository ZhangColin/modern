package com.cartisan.modern.account.domain;

import com.cartisan.modern.account.repository.AccountRepository;
import org.junit.Test;

import static com.cartisan.modern.common.callback.PostActionsFactory.failed;
import static com.cartisan.modern.common.callback.PostActionsFactory.success;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class AccountsTest {
    AccountRepository mockAccountRepository = mock(AccountRepository.class);
    Accounts accounts = new Accounts(mockAccountRepository);

    Account account = new Account();

    Runnable afterSuccess = mock(Runnable.class);
    Runnable afterFailed = mock(Runnable.class);

    @Test
    public void should_save_account() {
        accounts.add(account);

        verify(mockAccountRepository).save(account);
    }

    @Test
    public void should_return_success_when_save_with_no_exception(){
        accounts.add(account)
                .success(afterSuccess)
                .failed(afterFailed);

        verify(afterSuccess).run();
        verify(afterFailed, never()).run();
    }

    @Test
    public void should_return_failed_when_save_with_exception(){
        given_account_repository_save_will_failed();

        accounts.add(account)
                .success(afterSuccess)
                .failed(afterFailed);

        verify(afterSuccess, never()).run();
        verify(afterFailed).run();
    }

    private void given_account_repository_save_will_failed() {
        doThrow(IllegalArgumentException.class).when(mockAccountRepository).save(any(Account.class));
    }
}