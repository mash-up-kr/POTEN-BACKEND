package com.mashup.poten.dto.habit.response;

import com.mashup.poten.common.state.State;
import com.mashup.poten.domain.Habit;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HabitResponseDTO {
    private Integer habitSeq;

    private String title;

    private int duration;

    private String doDay;

    private int totalCount;

    private int doneCount;

    private int life;

    private int currentLife;

    private State state;

    private int alarmTime;

    private LocalDateTime createDate;

    private int characterCode;

    public static HabitResponseDTO fromDomain(Habit habit) {
        return new HabitResponseDTO(habit.getHabitSeq(), habit.getTitle(), habit.getDuration(), habit.getDoDay(), habit.getTotalCount(), habit.getDoneCount(), habit.getLife(), habit.getCurrentLife(), habit.getState(), habit.getAlarmTime(), habit.getCreateDate(), habit.getCharacterCode());
    }
}
