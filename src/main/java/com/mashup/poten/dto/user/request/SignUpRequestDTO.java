package com.mashup.poten.dto.user.request;

import com.mashup.poten.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDTO {

    private String snsType;

    private String token;

    private String nickname;

    public User toDomain() {
        return User.builder().snsType(snsType).token(token).nickname(nickname).build();
    }
}
