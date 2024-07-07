package com.akilesh.socialmedia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AkileshVasudevan
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

//    @GetMapping("/")
//    public String home() {
//        return "At Home";
//    }
}
