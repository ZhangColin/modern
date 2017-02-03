package com.cartisan.modern.account.domain;

public interface AccountPostActions {
    AccountPostActions success(Runnable afterSuccess);
    AccountPostActions failed(Runnable afterFailed);
    AccountPostActions nameDuplicated(Runnable afterNameDuplicated);
}
