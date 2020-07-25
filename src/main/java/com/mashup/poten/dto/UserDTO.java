package com.mashup.poten.dto;

import com.mashup.poten.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class UserDTO {

    private Integer userSeq;

    private String snsType;

    private String token;

    private String nickname;

    private List<AssignmentDTO> assignmentDTOs;

    public User toDomain() {
        return User.builder().snsType(snsType).token(token).nickname(nickname).build();
    }

    public static UserDTO fromDomain(User user) {
        return new UserDTO(user.getUserSeq(), user.getSnsType(), user.getToken(), user.getNickname(), user.getAssignments().stream().map(AssignmentDTO::fromDomain).collect(Collectors.toList()));
    }

    public void setToken(String newToken) {
        this.token = newToken;
    }

    public void setSortedForTodayAssignment(List<AssignmentDTO> assignmentDTOs) {
        this.assignmentDTOs = assignmentDTOs;
    }
}
