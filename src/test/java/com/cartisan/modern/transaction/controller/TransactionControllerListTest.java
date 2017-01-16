package com.cartisan.modern.transaction.controller;

import com.cartisan.modern.transaction.domain.Transaction;
import com.cartisan.modern.transaction.domain.Transactions;
import com.cartisan.modern.transaction.view.PresentableTransaction;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import static com.cartisan.modern.common.Formats.parseDay;
import static com.cartisan.modern.common.controller.Urls.TRANSACTION_LIST;
import static com.cartisan.modern.transaction.domain.Transaction.Type.Income;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TransactionControllerListTest {
    private static final Date DATE = parseDay("2016-08-15");
    private static final int AMOUNT = 100;
    private Transactions mockTransactions = mock(Transactions.class);
    private TransactionController controller = new TransactionController(mockTransactions);
    private Model mockModel = mock(Model.class);

    @Test
    public void go_to_transaction_list_page(){
        assertThat(showAllTransactions()).isEqualTo(TRANSACTION_LIST);
    }

    @Test
    public void show_all_transactions(){
        given_exists_transactions(transaction(Income, "Description", DATE, AMOUNT));

        showAllTransactions();

        ArgumentCaptor<List<PresentableTransaction>> captor = ArgumentCaptor.forClass((Class)List.class);
        verify(mockModel).addAttribute(eq("transactions"), captor.capture());
        captor.getValue().forEach(actual->assertThat(actual).isEqualToComparingFieldByField(
                expectedPresentableTransaction(Income, "Description", DATE, AMOUNT)
        ));
    }

    @Test
    public void show_no_transaction(){
        controller.noTransactionMessage = "no transaction message";

        showAllTransactions();

        verify(mockModel).addAttribute("message", "no transaction message");
        verify(mockModel).addAttribute("table.hidden", "hidden");
    }

    private PresentableTransaction expectedPresentableTransaction(Transaction.Type type, String description, Date date, int amount) {
        PresentableTransaction expected = new PresentableTransaction();
        expected.setType(type);
        expected.setDescription(description);
        expected.setDate(date);
        expected.setAmount(amount);

        return expected;
    }

    private void given_exists_transactions(Transaction transaction) {
        doAnswer(invocation->{
            Consumer<Transaction> consumer = (Consumer<Transaction>) invocation.getArguments()[0];
            consumer.accept(transaction);
            return null;
        }).when(mockTransactions).processAll(any(Consumer.class));
    }

    private Transaction transaction(Transaction.Type type, String description, Date date, int amount) {
        Transaction expected = new Transaction();
        expected.setType(type);
        expected.setDescription(description);
        expected.setDate(date);
        expected.setAmount(amount);

        return expected;
    }

    private String showAllTransactions() {
        return controller.showTransactions(mockModel);
    }
}
