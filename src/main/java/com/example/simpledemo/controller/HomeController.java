package com.example.simpledemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomeController {

    @GetMapping
    public String home(){
        return "Home";
    }

    @GetMapping("/{username}")
    public String checkUser(@PathVariable String username) {
        String input = username;
        if (input.equals("Jarek")) {
            return "Witaj Jarek!!!";
        } else {
            return "Nie znam!!!";
        }
    }
}
