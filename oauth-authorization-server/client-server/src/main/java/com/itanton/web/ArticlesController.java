package com.itanton.web;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
public class ArticlesController {

    private final WebClient webClient;

    public ArticlesController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping(value = "/messages")
    public List<String> getArticles(@RegisteredOAuth2AuthorizedClient("messages-client-authorization-code") OAuth2AuthorizedClient authorizedClient) {
        return Arrays.stream(this.webClient
                        .get()
                        .uri("http://localhost:8090/messages")
                        .attributes(oauth2AuthorizedClient(authorizedClient))
                        .retrieve()
                        .bodyToMono(String[].class)
                        .block())
                .toList();
    }
}