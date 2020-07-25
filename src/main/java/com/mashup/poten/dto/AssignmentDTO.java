package com.mashup.poten.dto;

import com.mashup.poten.domain.Assignment;
import com.mashup.poten.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class AssignmentDTO {

    private Integer assignmentSeq;

    private String title;

    private int duration;

    private String doDay;

    private int totalCount;

    private int doneCount;

    private String state;

    private int alarmTime;

    private LocalDateTime createDate;

    public static AssignmentDTO fromDomain(Assignment assignment) {
        return new AssignmentDTO(assignment.getAssignmentSeq(), assignment.getTitle(), assignment.getDuration(), assignment.getDoDay(), assignment.getTotalCount(), assignment.getDoneCount(), assignment.getState(), assignment.getAlarmTime(), assignment.getCreateDate());
    }

    public Assignment toDomain() {
        return Assignment.builder().title(title).duration(duration).doDay(doDay).totalCount(totalCount).doneCount(doneCount).state(state).alarmTime(alarmTime).createDate(createDate).build();
    }

}
