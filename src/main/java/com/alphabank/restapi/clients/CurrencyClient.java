package com.alphabank.restapi.clients;

import com.alphabank.restapi.dto.currency.GetHistoricalExchangeRatesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "currency", url = "${currency.api-url}")
public interface CurrencyClient {

    @GetMapping("/latest.json?app_id=${currency.api-key}")
    GetHistoricalExchangeRatesResponse getLatestExchangeRates(
            @RequestParam("symbols") String symbols
    );

    @GetMapping("/historical/{date}.json?app_id=${currency.api-key}")
    GetHistoricalExchangeRatesResponse getHistoricalExchangeRates(
            @PathVariable("date") String date,
            @RequestParam("symbols") String symbols
    );
}
