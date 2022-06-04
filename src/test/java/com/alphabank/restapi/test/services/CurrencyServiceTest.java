package com.alphabank.restapi.test.services;

import com.alphabank.restapi.clients.CurrencyClient;
import com.alphabank.restapi.services.CurrencyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static com.alphabank.restapi.test.MockResponses.getMockResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @Mock
    private CurrencyClient currencyClient;

    @InjectMocks
    private CurrencyService currencyService;

    @Test
    @DisplayName("It should return true when today rate is 100 and yesterday rate is 50")
    public void should_ReturnTrue_When_Rates100and50() {
        when(currencyClient.getLatestExchangeRates("RUB")).thenReturn(getMockResponse(100d));
        when(currencyClient.getHistoricalExchangeRates(anyString(), eq("RUB"))).thenReturn(getMockResponse(50d));

        assertThat(currencyService.isCurrencyRateRisingToday("RUB")).isTrue();
    }

    @Test
    @DisplayName("It should return false when today rate is 50 and yesterday rate is 100")
    public void should_ReturnFalse_When_Rates50and100() {
        when(currencyClient.getLatestExchangeRates("RUB")).thenReturn(getMockResponse(50d));
        when(currencyClient.getHistoricalExchangeRates(anyString(), eq("RUB"))).thenReturn(getMockResponse(100d));

        assertThat(currencyService.isCurrencyRateRisingToday("RUB")).isFalse();
    }

    @Test
    @DisplayName("It should throw an error when haven't got a rate from API")
    public void should_ThrowError_When_GotNoRates() {
        when(currencyClient.getHistoricalExchangeRates(anyString(), eq("RUB"))).thenReturn(getMockResponse());

        assertThatThrownBy(() -> currencyService.isCurrencyRateRisingToday("RUB"))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Currency with code RUB not found");
    }
}
