package com.cartisan.modern.common.callback;

public class PostActionsFactory {
    public static PostActions success() {
        return new SuccessPostActions();
    }

    public static PostActions failed() {
        return new FailedPostActions();
    }
}
