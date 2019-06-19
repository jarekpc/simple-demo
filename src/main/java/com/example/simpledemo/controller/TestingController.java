package com.example.simpledemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("testing2")
public class TestingController {

    @GetMapping
    public String testing(){
        return "Testing!2";
    }
}
