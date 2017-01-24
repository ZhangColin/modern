package com.cartisan.modern.common.builder;

import com.cartisan.modern.common.view.SignInView;

public class SignInViewBuilder {
    public static SignInView.SignInViewBuilder defaultSignInView(){
        return SignInView.builder().failedMessage("whatever message").logoutMessage("whatever message");
    }
}
