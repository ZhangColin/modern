package com.cartisan.modern.account.controller;

import com.cartisan.modern.account.domain.*;
import com.cartisan.modern.common.view.View;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.cartisan.modern.account.builder.AccountBuilder.defaultAccount;
import static com.cartisan.modern.common.controller.Urls.ACCOUNTS_ADD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class AccountControllerTest {
    Accounts mockAccounts = mock(Accounts.class);
    View<String> mockView = mock(View.class);
    AccountController controller = new AccountController(mockAccounts, mockView);
    Account account = defaultAccount().build();

    public class Add {
        @Test
        public void should_go_to_view() {
            assertThat(controller.addAccount()).isEqualTo(ACCOUNTS_ADD);
        }

    }
    public class SubmitAdd {
        @Before
        public void given_add_account_will_success() {
            given_add_account_will(new SuccessAccountPostActions());
        }

        @Before
        public void given_message() {
            controller.successMessage = "a success message";
            controller.failedMessage = "a failed message";
        }

        @Test
        public void should_go_to_view() {
            assertThat(submitAddAccount()).isEqualTo(ACCOUNTS_ADD);
        }

        @Test
        public void should_add_account() {
            submitAddAccount();

            verify(mockAccounts).add(account);
        }

        public class Success {
            @Test
            public void should_display_success_message() {
                given_add_account_will(new SuccessAccountPostActions());

                submitAddAccount();

                verify(mockView).display("a success message");
                verify(mockView, never()).display("a failed message");
            }

        }

        public class Failed {
            @Test
            public void should_display_failed_message(){
                given_add_account_will(new FailedAccountPostActions());

                submitAddAccount();

                verify(mockView, never()).display("a success message");
                verify(mockView).display("a failed message");
            }
        }

    }

    public class NameDuplicated {

        @Test
        public void should_display_name_duplicated_message(){
            given_add_account_will(new NameDuplicatedAccountPostActions());
            controller.nameDuplicatedMessage = "a name duplicated message";

            submitAddAccount();

            verify(mockView).display("a name duplicated message");
        }
    }
    private String submitAddAccount() {
        return controller.submitAddAccount(account);
    }

    private void given_add_account_will(AccountPostActions postActions) {
        when(mockAccounts.add(account)).thenReturn(postActions);
    }
}