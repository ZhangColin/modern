package com.cartisan.modern.transaction.domain;

import com.cartisan.modern.transaction.domain.summary.SummaryOfTransactions;
import com.cartisan.modern.transaction.repository.TransactionRepository;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.function.Consumer;

import static com.cartisan.modern.common.builder.PageableBuilder.defaultPageable;
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
        private Consumer<Transaction> whateverTransactionConsumer = transaction -> {};
        private Pageable whateverPageable = defaultPageable().build();

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

            processAll(whateverTransactionConsumer).withSummary(mockConsumer);

            verify(mockConsumer).accept(any(SummaryOfTransactions.class));
        }

        @Test
        public void should_pass_pageable_to_repository(){
            Pageable pageable = defaultPageable().build();

            transactions.processAll(whateverTransactionConsumer, pageable);

            verify(mockRepository).findAll(pageable);
        }

        private void given_findAll_will_return(Transaction transaction) {
            Page mockPage = mock(Page.class);
            doAnswer(invocation -> {
                Consumer consumer = invocation.getArgumentAt(0, Consumer.class);
                consumer.accept(transaction);
                return null;
            }).when(mockPage).forEach(any(Consumer.class));
            when(mockRepository.findAll(any(Pageable.class))).thenReturn(mockPage);
        }

        private TransactionPostActions processAll(Consumer<Transaction> whateverConsumer) {
            return transactions.processAll(whateverConsumer, whateverPageable);
        }

    }
}
