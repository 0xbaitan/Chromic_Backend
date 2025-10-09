package com.hexbaitan.chromic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping
    public String hello() {
        return "Hello World Oh dfsdnoddsfs!";
    }
}
