package com.cartisan.modern.common.controller;

import com.cartisan.modern.common.view.SignInView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.cartisan.modern.common.controller.Urls.SIGNIN;

@Controller
public class SessionController {
    private final SignInView signInView;

    @Autowired
    public SessionController(SignInView signInView) {
        this.signInView = signInView;
    }

    @GetMapping(SIGNIN)
    public String signIn(@RequestParam(value = "error", required = false)String error,
                               @RequestParam(value = "logout", required = false)String logout){
        signInView.display(error, logout);

        return SIGNIN;
    }
}
