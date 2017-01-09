package com.cartisan.modern.home.controller;

import com.cartisan.modern.common.Outbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {
    @Autowired
    private Outbox outbox;

    @RequestMapping(value = {"/"}, method = GET)
    public String index(Model model){
        return "home";
    }

}
