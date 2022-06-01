package com.alphabank.restapi.controllers;

import com.alphabank.restapi.services.CurrencyService;
import com.alphabank.restapi.services.GifService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public record MainController(CurrencyService currencyService, GifService gifService) {

    @GetMapping("/{currencyCode}")
    public String getCurrencyRateGif(@PathVariable String currencyCode) {
        var gifQuery = currencyService.isCurrencyRateRisingToday(currencyCode) ? "rich" : "broke";

        return String.format("<img src=\"%s\" />", gifService.getRandomGifUrlBySearch(gifQuery));
    }
}
