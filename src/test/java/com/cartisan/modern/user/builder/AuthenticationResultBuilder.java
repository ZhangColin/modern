package com.cartisan.modern.user.builder;

import com.cartisan.modern.user.domain.AuthenticationResult;

import static com.cartisan.modern.user.domain.AuthenticationResult.builder;

public class AuthenticationResultBuilder {
    public static AuthenticationResult.AuthenticationResultBuilder defaultAuthenticationResult() {
        return builder().error(null).logout(null);
    }
}
