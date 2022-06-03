package com.alphabank.restapi.services;

import com.alphabank.restapi.clients.GifClient;
import com.alphabank.restapi.configurations.ApiKeysConfig;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@Service
public record GifService(GifClient gifClient, ApiKeysConfig apiKeysConfig) {
    public String getRandomGifUrlBySearch(String query) {
        var totalCount = gifClient.getGifBySearch(
                apiKeysConfig.getGifApiKey(),
                query,
                1,
                0,
                "g",
                "en"
        ).pagination().total_count();

        if (totalCount == 0)
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("GIFs with query %s not found", query)
            );

        var random = new Random();

        return gifClient.getGifBySearch(
                apiKeysConfig.getGifApiKey(),
                query,
                1,
                random.nextInt(totalCount),
                "g",
                "en"
        ).data()[0].images().original().url();
    }
}
