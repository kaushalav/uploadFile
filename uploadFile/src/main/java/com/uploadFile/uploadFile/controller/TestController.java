package com.uploadFile.uploadFile.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/home")
    public String welcome() {
        return "Welcome to the upload image site";
    }
}
