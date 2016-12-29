package com.cartisan.modern.transaction.domain;

import com.cartisan.modern.transaction.repository.TransactionRepository;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TransactionsTest {
    @Test
    public void save_transaction(){
        TransactionRepository repository = mock(TransactionRepository.class);
        Transactions transactions = new Transactions(repository);

        Transaction transaction = new Transaction();
        transactions.add(transaction);

        verify(repository).save(transaction);
    }
}
