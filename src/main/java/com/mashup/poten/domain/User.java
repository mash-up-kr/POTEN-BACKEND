package com.mashup.poten.domain;

import com.mashup.poten.dto.AssignmentDTO;
import com.mashup.poten.dto.UserDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User Domain 객체
 * 유저에 대한 비즈니스 로직을 담당
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer userSeq;

    private String snsType;

    private String token;

    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<Assignment> assignments = new ArrayList<>();

    @Builder
    public User(Integer userSeq, String snsType, String token, String nickname) {
        this.userSeq = userSeq;
        this.snsType = snsType;
        this.token = token;
        this.nickname = nickname;
    }

    public void encodingPassword(String token) {
        this.token = token;
    }

    public void setSortedForToday(UserDTO userDTO) {
        LocalDate date = LocalDate.now();

        List<Assignment> todayAssignments = assignments.stream().filter(assignment -> assignment.getDoDay().contains(date.getDayOfWeek().toString())).sorted().collect(Collectors.toList());
        List<Assignment> notTodayAssignments = assignments.stream().filter(assignment -> !assignment.getDoDay().contains(date.getDayOfWeek().toString())).sorted().collect(Collectors.toList());

        todayAssignments.addAll(notTodayAssignments);

        userDTO.setSortedForTodayAssignment(todayAssignments.stream().map(AssignmentDTO::fromDomain).collect(Collectors.toList()));
    }
}
