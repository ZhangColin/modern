package com.cartisan.modern.home.controller;

import com.cartisan.modern.home.view.Home;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.cartisan.modern.common.controller.Urls.ROOT;

@Controller
@RequestMapping(ROOT)
public class HomeController {
    @Autowired
    private Home home;

    @GetMapping
    public ModelAndView index(){
        return home;
    }
}
