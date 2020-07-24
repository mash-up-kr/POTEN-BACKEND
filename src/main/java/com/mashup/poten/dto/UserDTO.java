package com.mashup.poten.dto;

import com.mashup.poten.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDTO {
    private Integer userSeq;
    private String userId;
    private String userPassword;

    public User toDomain() {
        return User.builder().userId(userId).userPassword(userPassword).build();
    }

    public static UserDTO fromDomain(User user) {
        return new UserDTO(user.getUserSeq(), user.getUserId(), user.getUserPassword());
    }
}
