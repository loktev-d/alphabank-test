package com.alphabank.restapi.clients;

import com.alphabank.restapi.dto.currency.GetHistoricalExchangeRatesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("currency")
public interface CurrencyClient {

    @RequestMapping(method = RequestMethod.GET, value = "/historical/{date}.json?app_id={appId}&symbols={symbols}")
    GetHistoricalExchangeRatesResponse getHistoricalExchangeRates(
            @PathVariable("date") String date,
            @PathVariable("appId") String appId,
            @PathVariable("symbols") String symbols
    );
}
