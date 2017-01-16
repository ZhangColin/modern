package com.cartisan.modern.transaction.domain;

import com.cartisan.modern.transaction.repository.TransactionRepository;
import org.junit.Test;

import java.util.function.Consumer;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

public class TransactionsTest {
    TransactionRepository mockRepository = mock(TransactionRepository.class);
    Transactions transactions = new Transactions(mockRepository);
    Transaction transaction = new Transaction();

    Runnable afterSuccess = mock(Runnable.class);
    Runnable afterFailed = mock(Runnable.class);

    @Test
    public void save_transaction() {
        transactions.add(transaction);
        verify(mockRepository).save(transaction);
    }

    @Test
    public void call_after_success_when_save_successfully() {
        transactions.add(transaction).success(afterSuccess).failed(afterFailed);

        verify(afterSuccess).run();
        verify(afterFailed, never()).run();
    }

    @Test
    public void call_after_failed_when_save_failed(){
        given_save_will_fail();

        transactions.add(transaction).success(afterSuccess).failed(afterFailed);

        verify(afterFailed).run();
        verify(afterSuccess, never()).run();
    }

    @Test
    public void process_all_transactions(){
        when(mockRepository.findAll()).thenReturn(asList(transaction));

        Consumer<Transaction> mockConsumber = mock(Consumer.class);
        transactions.processAll(mockConsumber);

        verify(mockConsumber).accept(transaction);
    }

    private void given_save_will_fail() {
        doThrow(IllegalArgumentException.class).when(mockRepository).save(any(Transaction.class));
    }


}
