package com.cartisan.modern.transaction.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionControllerTest {
    @Test
    public void back_page(){
        TransactionController controller = new TransactionController();

        assertEquals("add_transaction", controller.confirm());
    }
}