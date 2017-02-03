package com.cartisan.modern.account.controller;

import com.cartisan.modern.account.domain.Account;
import com.cartisan.modern.account.domain.Accounts;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.cartisan.modern.common.controller.Urls.ACCOUNT_ADD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(NestedRunner.class)
public class AccountControllerTest {
    Accounts mockAccounts = mock(Accounts.class);
    AccountController controller = new AccountController(mockAccounts);

    public class Add {
        @Test
        public void should_go_to_view() {
            assertThat(controller.addAccount()).isEqualTo(ACCOUNT_ADD);
        }
    }

    public class AddAccountSuccess {
        @Test
        public void should_go_to_view() {
            assertThat(controller.submitAddAccount(new Account())).isEqualTo(ACCOUNT_ADD);
        }

        @Test
        public void should_add_account(){
            Account account = new Account();

            controller.submitAddAccount(account);

            verify(mockAccounts).add(account);
        }
    }
}