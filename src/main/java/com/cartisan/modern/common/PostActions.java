package com.cartisan.modern.common;

public interface PostActions {
    PostActions success(Runnable afterSuccess);
    PostActions failed(Runnable afterFailed);
}
