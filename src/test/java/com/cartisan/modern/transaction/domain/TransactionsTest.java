package com.cartisan.modern.transaction.domain;

import com.cartisan.modern.transaction.repository.TransactionRepository;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TransactionsTest {
    private final Runnable whatever = () -> {
    };
    TransactionRepository repository = mock(TransactionRepository.class);
    Transactions transactions = new Transactions(repository);
    Transaction transaction = new Transaction();

    @Test
    public void save_transaction() {
        transactions.add(transaction, whatever);
        verify(repository).save(transaction);
    }

    @Test
    public void call_after_success_when_save_successfully() {
        Runnable afterSuccess = mock(Runnable.class);
        transactions.add(transaction, afterSuccess);

        verify(afterSuccess).run();
    }
}
