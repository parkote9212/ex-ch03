package com.gc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyThymeleaf {

    @GetMapping("/hello")
    public String hello() {
        return "hello";

    }



    @GetMapping("/hello_thyme")
    public String helloThyme(Model model) {
        model.addAttribute("message", "스프링부트 타임리프 화면");
        return "hello_thyme";
    }

    @GetMapping("/hello_mu")
    public String helloMu(Model model) {
        model.addAttribute("message", "머스테지머스테치");
        return "hello_mu";

    }

}