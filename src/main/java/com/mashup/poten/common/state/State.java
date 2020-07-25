package com.mashup.poten.common.state;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum State {
    ING("ING"), DONE("DONE"), FAIL("FAIL");

    private final String status;

}
