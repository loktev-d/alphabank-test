package com.alphabank.restapi.dto.currency;

import java.util.Map;

public record GetHistoricalExchangeRatesResponse(
        Map<String, Double> rates
) {
}
