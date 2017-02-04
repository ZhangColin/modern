package com.cartisan.modern.account.domain;

public class SuccessAccountPostActions implements AccountPostActions {
    @Override
    public AccountPostActions success(Runnable afterSuccess) {
        afterSuccess.run();
        return this;
    }
}
