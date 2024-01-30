package com.dualmeidalima.concurrentexecutions;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class JSONPlaceholderService {

    private RestTemplate restTemplate;

    public JSONPlaceholderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<JsonNode> getPosts(int i) {
        var response = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/" + i, JsonNode.class);

        System.out.println("response: " + response.toString());
        return CompletableFuture.completedFuture(response);
    }
}
