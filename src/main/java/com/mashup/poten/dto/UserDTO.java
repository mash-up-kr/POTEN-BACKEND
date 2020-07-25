package com.mashup.poten.dto;

import com.mashup.poten.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDTO {

    private Integer userSeq;

    private String snsType;

    private String token;

    public User toDomain() {
        return User.builder().snsType(snsType).token(token).build();
    }

    public static UserDTO fromDomain(User user) {
        return new UserDTO(user.getUserSeq(), user.getSnsType(), user.getToken());
    }

    public void setToken(String newToken) {
        this.token = newToken;
    }
}
