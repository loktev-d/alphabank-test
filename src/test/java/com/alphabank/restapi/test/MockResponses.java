package com.alphabank.restapi.test;

import com.alphabank.restapi.dto.currency.GetExchangeRatesResponse;
import com.alphabank.restapi.dto.gif.*;

import java.util.HashMap;

public class MockResponses {

    public static GetExchangeRatesResponse getMockResponse(double mockRate) {
        var todayRates = new HashMap<String, Double>();
        todayRates.put("RUB", mockRate);

        return new GetExchangeRatesResponse(todayRates);
    }

    public static GetExchangeRatesResponse getMockResponse() {
        var todayRates = new HashMap<String, Double>();

        return new GetExchangeRatesResponse(todayRates);
    }

    public static GetGifBySearchResponse getMockResponse(String mockUrl, int totalCount) {
        return new GetGifBySearchResponse(
                new DataItem[]{new DataItem(new Images(new Original(mockUrl)))},
                new Pagination(totalCount)
        );
    }

    public static GetGifBySearchResponse getMockResponse(int totalCount) {
        return new GetGifBySearchResponse(
                new DataItem[]{},
                new Pagination(totalCount)
        );
    }
}
