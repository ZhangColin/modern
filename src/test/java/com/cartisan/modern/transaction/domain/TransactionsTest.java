package com.cartisan.modern.transaction.domain;

import com.cartisan.modern.transaction.repository.TransactionRepository;
import org.junit.Test;

import static com.cartisan.modern.transaction.domain.RunnableHelper.WHATEVER;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TransactionsTest {
    TransactionRepository repository = mock(TransactionRepository.class);
    Transactions transactions = new Transactions(repository);
    Transaction transaction = new Transaction();

    @Test
    public void save_transaction() {
        transactions.add(transaction, WHATEVER, WHATEVER);
        verify(repository).save(transaction);
    }

    @Test
    public void call_after_success_when_save_successfully() {
        Runnable afterSuccess = mock(Runnable.class);
        transactions.add(transaction, afterSuccess, WHATEVER);

        verify(afterSuccess).run();
    }
}
