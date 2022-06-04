package com.alphabank.restapi.dto.currency;

import java.util.Map;

public record GetExchangeRatesResponse(
        Map<String, Double> rates
) {
}
