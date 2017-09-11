package com.fndsoft.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by feibabm on 2017/9/6 0006.
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}
