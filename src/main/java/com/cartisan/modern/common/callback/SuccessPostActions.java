package com.cartisan.modern.common.callback;

public class SuccessPostActions implements PostActions {
    @Override
    public PostActions success(Runnable afterSuccess) {
        afterSuccess.run();
        return this;
    }
}
