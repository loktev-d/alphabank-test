package com.alphabank.restapi.services;

import com.alphabank.restapi.clients.CurrencyClient;
import com.alphabank.restapi.configurations.ApiKeysConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public record CurrencyService(CurrencyClient currencyClient, ApiKeysConfig apiKeysConfig) {

    public boolean isCurrencyRateRisingToday(String currencyCode) {
        var yesterdayRate = currencyClient.getHistoricalExchangeRates(
                LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                apiKeysConfig.getCurrencyApiKey(),
                currencyCode
        ).rates().get(currencyCode);

        var todayRate = currencyClient.getHistoricalExchangeRates(
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                apiKeysConfig.getCurrencyApiKey(),
                currencyCode
        ).rates().get(currencyCode);

        return todayRate > yesterdayRate;
    }
}
