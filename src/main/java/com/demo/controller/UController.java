package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UController {
    @GetMapping("/login")
    public String login() {
        return "login page";
    }

    @GetMapping("/home")
    public String home() {
        return "home page";
    }

}
