package com.alphabank.restapi.services;

import com.alphabank.restapi.clients.CurrencyClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public record CurrencyService(CurrencyClient currencyClient) {

    public boolean isCurrencyRateRisingToday(String currencyCode) {
        var yesterdayRate = currencyClient.getHistoricalExchangeRates(
                LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                currencyCode
        ).rates().get(currencyCode.toUpperCase());

        if (yesterdayRate == null)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Currency with code %s not found", currencyCode)
            );

        var todayRate = currencyClient.getLatestExchangeRates(
                currencyCode
        ).rates().get(currencyCode.toUpperCase());

        return todayRate > yesterdayRate;
    }
}
