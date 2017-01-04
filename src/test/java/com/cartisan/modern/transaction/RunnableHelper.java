package com.cartisan.modern.transaction;

import org.mockito.stubbing.Answer;

public class RunnableHelper {
    public static final Runnable WHATEVER = () -> {};

    public static Answer createRunnableArgumentInvoker(int index){
        return invocation -> {
            ((Runnable)invocation.getArguments()[index]).run();
            return null;
        };
    }
}
