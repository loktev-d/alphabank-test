package com.alphabank.restapi.clients;

import com.alphabank.restapi.dto.gif.GetGifBySearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("gifs")
public interface GifClient {

    @RequestMapping(method = RequestMethod.GET, value = "/search?api_key={apiKey}&q={query}&limit={limit}&offset={offset}&rating={rating}&lang={lang}")
    GetGifBySearchResponse getGifBySearch(
            @PathVariable("apiKey") String apiKey,
            @PathVariable("query") String query,
            @PathVariable("limit") int limit,
            @PathVariable("offset") int offset,
            @PathVariable("rating") String rating,
            @PathVariable("lang") String lang
    );
}
