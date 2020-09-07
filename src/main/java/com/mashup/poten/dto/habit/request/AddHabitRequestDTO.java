package com.mashup.poten.dto.habit.request;

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
public class AddHabitRequestDTO {

    private String title;

    private int duration;

    private String doDay;

    private int alarmTime;

    private int characterCode;

    public Habit toDomain() {
        return Habit.builder().title(title).duration(duration).doDay(doDay).alarmTime(alarmTime).characterCode(characterCode).build();
    }

    public void removeDoDaySpace() {
        this.doDay = this.doDay.replaceAll(" ", "");
    }

}
