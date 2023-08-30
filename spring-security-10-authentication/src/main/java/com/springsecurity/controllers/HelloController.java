package com.springsecurity.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
    @GetMapping("/hello")
    public String hello() {
        return "hello.html";
    }
}
