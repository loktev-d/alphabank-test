package com.alphabank.restapi.dto.gif;

public record GetGifBySearchResponse(
        DataItem[] data,
        Pagination pagination
) {
}
