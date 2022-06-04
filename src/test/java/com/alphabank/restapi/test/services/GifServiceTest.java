package com.alphabank.restapi.test.services;

import com.alphabank.restapi.clients.GifClient;
import com.alphabank.restapi.services.GifService;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GifServiceTest {

    @Mock
    private GifClient gifClient;

    @InjectMocks
    private GifService gifService;

    @Test
    @DisplayName("It should return GIF url when query is given")
    public void should_ReturnString_When_QueryGiven() {
        when(gifClient.getGifBySearch(eq("rich"), eq(1), intThat(argument -> argument < 1000)))
                .thenReturn(getMockResponse("rich_gif_url", 1000));

        assertThat(gifService.getRandomGifUrlBySearch("rich")).isEqualTo("rich_gif_url");
    }

    @Test
    @DisplayName("It should throw an error when no items have been found by query")
    public void should_ThrowError_When_NoItemsFound() {
        when(gifClient.getGifBySearch(eq("rich"), eq(1), anyInt()))
                .thenReturn(getMockResponse(0));

        assertThatThrownBy(() -> gifService.getRandomGifUrlBySearch("rich"))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("GIFs with query rich not found");
    }
}
