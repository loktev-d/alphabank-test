package com.alphabank.restapi.controllers;

import com.alphabank.restapi.services.CurrencyService;
import com.alphabank.restapi.services.GifService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public record MainController(CurrencyService currencyService, GifService gifService) {

    @GetMapping("/api")
    public String getCurrencyRateGif(@RequestParam(value = "code", defaultValue = "${currency.default-currency-code}") String currencyCode) {
        var gifQuery = currencyService.isCurrencyRateRisingToday(currencyCode) ? "rich" : "broke";

        return String.format("<img src=\"%s\" />", gifService.getRandomGifUrlBySearch(gifQuery));
    }
}
