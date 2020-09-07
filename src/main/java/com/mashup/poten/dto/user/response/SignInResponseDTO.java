package com.mashup.poten.dto.user.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInResponseDTO {

    private Integer userSeq;

    private String token;

    private String nickname;
}
