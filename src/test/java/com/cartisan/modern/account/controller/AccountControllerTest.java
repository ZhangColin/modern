package com.cartisan.modern.account.controller;

import com.cartisan.modern.account.domain.Account;
import com.cartisan.modern.account.domain.Accounts;
import com.cartisan.modern.common.view.View;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.cartisan.modern.common.controller.Urls.ACCOUNTS_ADD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(NestedRunner.class)
public class AccountControllerTest {
    Accounts mockAccounts = mock(Accounts.class);
    View<String> mockView = mock(View.class);
    AccountController controller = new AccountController(mockAccounts, mockView);

    public class Add {
        @Test
        public void should_go_to_view() {
            assertThat(controller.addAccount()).isEqualTo(ACCOUNTS_ADD);
        }
    }

    public class AddAccountSuccess {
        Account account = new Account();

        @Test
        public void should_go_to_view() {
            assertThat(submitAddAccount()).isEqualTo(ACCOUNTS_ADD);
        }

        @Test
        public void should_add_account(){
            submitAddAccount();

            verify(mockAccounts).add(account);
        }

        @Test
        public void should_display_success_message() {
            controller.successMessage = "a success message";

            submitAddAccount();

            verify(mockView).display("a success message");
        }

        private String submitAddAccount() {
            return controller.submitAddAccount(account);
        }
    }
}