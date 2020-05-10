package com.easybudget.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HelloController {

    @GetMapping(path = "/hello")
    public Map<String, String> getHello() {
        Map<String, String> mapHello = new HashMap<>();
        mapHello.put("message", "Epale");
        return mapHello;
    }
}
