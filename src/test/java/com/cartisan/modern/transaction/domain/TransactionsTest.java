package com.cartisan.modern.transaction.domain;

import com.cartisan.modern.common.controller.ResultRange;
import com.cartisan.modern.transaction.domain.summary.SummaryOfTransactions;
import com.cartisan.modern.transaction.repository.TransactionRepository;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.function.Consumer;

import static com.cartisan.modern.common.builder.ResultRangeBuilder.defaultResultRange;
import static com.cartisan.modern.transaction.builder.TransactionBuilder.defaultTransaction;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

@RunWith(NestedRunner.class)
public class TransactionsTest {
    TransactionRepository mockRepository = mock(TransactionRepository.class);
    Transactions transactions = new Transactions(mockRepository);
    Transaction transaction = defaultTransaction().build();

    Runnable afterSuccess = mock(Runnable.class);
    Runnable afterFailed = mock(Runnable.class);

    public class Save{
        @Test
        public void should_save_transaction() {
            transactions.add(transaction);
            verify(mockRepository).save(transaction);
        }

        @Test
        public void should_call_after_success_when_save_successfully() {
            transactions.add(transaction).success(afterSuccess).failed(afterFailed);

            verify(afterSuccess).run();
            verify(afterFailed, never()).run();
        }

        @Test
        public void should_call_after_failed_when_save_failed(){
            given_save_will_fail();

            transactions.add(transaction).success(afterSuccess).failed(afterFailed);

            verify(afterFailed).run();
            verify(afterSuccess, never()).run();
        }

        private void given_save_will_fail() {
            doThrow(IllegalArgumentException.class).when(mockRepository).save(any(Transaction.class));
        }

    }

    public class ProcessAll{
        private Consumer<Transaction> whateverConsumer = transaction -> {};
        private ResultRange whateverResultRange = defaultResultRange().build();

        @Before
        public void given_findAll_will_return_transaction(){
            given_findAll_will_return(transaction);
        }

        @Test
        public void should_process_all_transactions(){
            Consumer<Transaction> mockConsumer = mock(Consumer.class);
            processAll(mockConsumer);

            verify(mockConsumer).accept(transaction);
        }

        @Test
        public void process_all_transactions_with_summary(){
            Consumer<SummaryOfTransactions> mockConsumer = mock(Consumer.class);

            processAll(whateverConsumer).withSummary(mockConsumer);

            verify(mockConsumer).accept(any(SummaryOfTransactions.class));
        }

        private TransactionPostActions processAll(Consumer<Transaction> whateverConsumer) {
            return transactions.processAll(whateverConsumer, whateverResultRange);
        }

        private void given_findAll_will_return(Transaction transaction) {
            when(mockRepository.findAll()).thenReturn(asList(transaction));
        }

    }
}
