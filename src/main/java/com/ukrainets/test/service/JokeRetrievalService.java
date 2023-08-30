package com.ukrainets.test.service;

import com.ukrainets.test.dto.JokeDto;
import com.ukrainets.test.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class JokeRetrievalService {

    private static final String JOKES_URL = "https://official-joke-api.appspot.com/random_joke";

    public List<JokeDto> getJokes(int amount) {
        List<JokeDto> jokes = new ArrayList<>();
        if (amount > 100 || amount < 1) {
            throw new CustomException("You can retrieve only 100 jokes at a time");
        }
        for (int i = 0; i < amount; i++) {
            jokes.add(getJokesFromApi().join());
        }
        return jokes;
    }

    private CompletableFuture<JokeDto> getJokesFromApi() {
        return WebClient.create(JOKES_URL).method(HttpMethod.GET)
                .retrieve()
                .bodyToMono(JokeDto.class)
                .timeout(Duration.ofSeconds(5L))
                // subscribe on a different thread from the given scheduler to avoid blocking as toFuture is a subscriber
                .subscribeOn(Schedulers.single())
                // subscribes to the mono and converts it to a completable future
                .toFuture();
    }
}
