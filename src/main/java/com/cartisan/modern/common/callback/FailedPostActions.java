package com.cartisan.modern.common.callback;

public class FailedPostActions implements PostActions {
    @Override
    public PostActions failed(Runnable afterFailed) {
        afterFailed.run();
        return this;
    }
}
