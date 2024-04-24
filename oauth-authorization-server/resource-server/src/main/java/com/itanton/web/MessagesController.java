package com.itanton.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MessagesController {

    @GetMapping("/messages")
    public List<String> getArticles(@RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            System.out.printf("Header '%s' = %s%n", key, value);
        });
        return List.of("Hello", "World");
    }
}