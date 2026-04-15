package com.example.TO_DO.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class home {

    @GetMapping("/home")
    public String hello(@RequestParam(defaultValue = "World") String name) {
        return "Hello " + name + "!";
    }
}
