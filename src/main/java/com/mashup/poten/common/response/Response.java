package com.mashup.poten.common.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Java version 1.6인가 1.5부터
 * Generic Type is added
 *
 *
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Response<T> {
    private Integer responseCode;
    private T responseData;

    @Builder
    public Response(Integer responseCode, T responseData) {
        this.responseCode = responseCode;
        this.responseData = responseData;
    }
}
