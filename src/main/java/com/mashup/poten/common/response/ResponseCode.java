package com.mashup.poten.common.response;

public class ResponseCode {
    // 성공
    public static final int SUCCESS = 1;

    // 실패
    public static final int FAIL = 0;

    // INVALID_JSON
    public static final int FAIL_DUE_TO_INVALID_JSON = 2;

    // 회원이 아님
    public static final int NEED_TO_SIGN_UP = 3;
}
