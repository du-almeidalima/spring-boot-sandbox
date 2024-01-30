package com.dualmeidalima.concurrentexecutions;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class ConcurrentRunner implements CommandLineRunner {

        private JSONPlaceholderService jsonPlaceholderService;

        public ConcurrentRunner(JSONPlaceholderService jsonPlaceholderService) {
            this.jsonPlaceholderService = jsonPlaceholderService;
        }

        @Override
        public void run(String... args) throws Exception {
            Instant start = Instant.now();
            List<CompletableFuture<JsonNode>> allFutures = new ArrayList<>();

            for (int i = 1; i < 10; i++) {
                allFutures.add(jsonPlaceholderService.getPosts(i));
            }

            System.out.println("This should be printed before the responses");
            CompletableFuture.allOf(allFutures.toArray(new CompletableFuture[]{}));

//            for (int i = 0; i < 10; i++) {
//                System.out.println("response: " + allFutures.get(i).get().toString());
//            }

            System.out.println("Total time: " + Duration.between(start, Instant.now()).getSeconds());
        }
}
