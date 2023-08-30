package com.ukrainets.test.controller;

import com.ukrainets.test.dto.JokeDto;
import com.ukrainets.test.service.JokeRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final JokeRetrievalService jokeRetrievalService;

    @GetMapping("/")
    public List<JokeDto> getJokes(@RequestParam int amount) {
        return jokeRetrievalService.getJokes(amount);
    }
}
