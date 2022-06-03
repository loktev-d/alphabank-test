package com.alphabank.restapi.clients;

import com.alphabank.restapi.dto.gif.GetGifBySearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gif", url = "${gif.api-url}")
public interface GifClient {

    @GetMapping("/search?api_key=${gif.api-key}&rating=${gif.rating}&lang=${gif.lang}")
    GetGifBySearchResponse getGifBySearch(
            @RequestParam("query") String query,
            @RequestParam("limit") int limit,
            @RequestParam("offset") int offset
    );
}
