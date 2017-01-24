package com.cartisan.modern.user.builder;

import com.cartisan.modern.user.view.SignInView;

public class SignInViewBuilder {
    public static SignInView.SignInViewBuilder defaultSignInView(){
        return SignInView.builder().failedMessage("whatever message").logoutMessage("whatever message");
    }
}
