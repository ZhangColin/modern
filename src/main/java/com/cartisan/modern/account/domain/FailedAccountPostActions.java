package com.cartisan.modern.account.domain;

public class FailedAccountPostActions implements AccountPostActions {
    @Override
    public AccountPostActions failed(Runnable afterFailed) {
        afterFailed.run();
        return this;
    }
}
