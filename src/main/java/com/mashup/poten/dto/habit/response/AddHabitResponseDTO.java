package com.mashup.poten.dto.habit.response;

import com.mashup.poten.common.state.State;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddHabitResponseDTO {
    private Integer habitSeq;

    private String title;

    private int duration;

    private String doDay;

    private int totalCount;

    private int doneCount;

    private int life;

    private State state;

    private int alarmTime;

    private LocalDateTime createDate;

    private int characterCode;
}
