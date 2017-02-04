package com.cartisan.modern.common.callback;

public interface PostActions {
    default PostActions success(Runnable afterSuccess){
        return this;
    }

    default PostActions failed(Runnable afterFailed){
        return this;
    }
}
