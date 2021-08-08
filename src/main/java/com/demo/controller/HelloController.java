package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")

@Slf4j
public class HelloController {

    @GetMapping
    public String sayHello() {
        log.info("HelloController-->sayHello()...");
        return "Hello SpringBoot！";
    }

}
