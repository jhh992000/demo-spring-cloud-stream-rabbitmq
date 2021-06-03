package com.example.common.app.controller;

import com.example.core.util.CommonUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @GetMapping("/hello")
    public String hello(String name) {
        if (CommonUtils.isNull(name)) {
            return "Hello World";
        }
        return "Hello " + name;
    }
}
