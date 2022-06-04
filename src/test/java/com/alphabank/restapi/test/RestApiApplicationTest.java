package com.alphabank.restapi.test;

import com.alphabank.restapi.clients.CurrencyClient;
import com.alphabank.restapi.clients.GifClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.alphabank.restapi.test.MockResponses.getMockResponse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestApiApplicationTest {

    @MockBean
    private CurrencyClient currencyClient;

    @MockBean
    private GifClient gifClient;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Is should return rich GIF when rate of currency with given code is rising")
    public void should_ReturnRichGif_When_RateRising() throws Exception {
        when(currencyClient.getLatestExchangeRates("RUB")).thenReturn(getMockResponse(100d));
        when(currencyClient.getHistoricalExchangeRates(anyString(), eq("RUB"))).thenReturn(getMockResponse(50d));

        when(gifClient.getGifBySearch(eq("rich"), eq(1), intThat(argument -> argument < 1000)))
                .thenReturn(getMockResponse("rich_gif_url", 1000));

        mockMvc.perform(get("/api?code=RUB")).andExpectAll(
                status().isOk(),
                content().string("<img src=\"rich_gif_url\" />")
        );
    }

    @Test
    @DisplayName("Is should return broke GIF when rate of currency with given code is not rising")
    public void should_ReturnBrokeGif_When_RateNotRising() throws Exception {
        when(currencyClient.getLatestExchangeRates("RUB")).thenReturn(getMockResponse(50d));
        when(currencyClient.getHistoricalExchangeRates(anyString(), eq("RUB"))).thenReturn(getMockResponse(100d));

        when(gifClient.getGifBySearch(eq("broke"), eq(1), intThat(argument -> argument < 1000)))
                .thenReturn(getMockResponse("broke_gif_url", 1000));

        mockMvc.perform(get("/api?code=RUB")).andExpectAll(
                status().isOk(),
                content().string("<img src=\"broke_gif_url\" />")
        );
    }
}
