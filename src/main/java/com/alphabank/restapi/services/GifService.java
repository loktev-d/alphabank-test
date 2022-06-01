package com.alphabank.restapi.services;

import com.alphabank.restapi.clients.GifClient;
import com.alphabank.restapi.configurations.ApiKeys;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public record GifService(GifClient gifClient, ApiKeys apiKeysConfig) {
    public String getRandomGifUrlBySearch(String query) {
        var random = new Random();

        return gifClient.getGifBySearch(
                        apiKeysConfig.gifApiKey(),
                        query,
                        1,
                        random.nextInt(10_000),
                        "g",
                        "en")
                .data().images().original().url();
    }
}