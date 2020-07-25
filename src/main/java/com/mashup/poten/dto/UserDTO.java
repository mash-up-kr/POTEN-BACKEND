package com.mashup.poten.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mashup.poten.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class UserDTO {

    private Integer userSeq;

    private String snsType;

    private String token;

    private String nickname;

    @JsonProperty("habits")
    private List<HabitDTO> habitDTOs = new ArrayList<>();

    public User toDomain() {
        return User.builder().snsType(snsType).token(token).nickname(nickname).build();
    }

    public static UserDTO fromDomain(User user) {
        return new UserDTO(user.getUserSeq(), user.getSnsType(), user.getToken(), user.getNickname(), user.getHabits().stream().map(HabitDTO::fromDomain).collect(Collectors.toList()));
    }

    public void setToken(String newToken) {
        this.token = newToken;
    }

    public void setSortedForTodayHabit(List<HabitDTO> habitDTOs) {
        this.habitDTOs = habitDTOs;
    }
}
