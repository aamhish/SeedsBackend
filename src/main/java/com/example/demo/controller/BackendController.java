package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController {
    int count = 0;
    @GetMapping("/hi")
    public String sayHi() {
        return "hi";
    }
    @GetMapping("/count")
    public int count() {
        count +=1;
        return count;
    }
}
