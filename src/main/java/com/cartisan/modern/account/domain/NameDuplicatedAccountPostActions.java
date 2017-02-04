package com.cartisan.modern.account.domain;

public class NameDuplicatedAccountPostActions implements  AccountPostActions {
    @Override
    public AccountPostActions nameDuplicated(Runnable afterNameDuplicated) {
        afterNameDuplicated.run();
        return this;
    }
}
