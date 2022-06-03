package com.alphabank.restapi.services;

import com.alphabank.restapi.clients.GifClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@Service
public record GifService(GifClient gifClient) {

    public String getRandomGifUrlBySearch(String query) {
        var totalCount = gifClient.getGifBySearch(
                query,
                1,
                0
        ).pagination().total_count();

        if (totalCount == 0)
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("GIFs with query %s not found", query)
            );

        var random = new Random();

        return gifClient.getGifBySearch(
                query,
                1,
                random.nextInt(totalCount)
        ).data()[0].images().original().url();
    }
}
