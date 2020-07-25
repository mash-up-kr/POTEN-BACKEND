package com.mashup.poten.dto;

import com.mashup.poten.domain.Habit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class HabitDTO {

    private Integer habitSeq;

    private String title;

    private int duration;

    private String doDay;

    private int totalCount;

    private int doneCount;

    private String state;

    private int alarmTime;

    private LocalDateTime createDate;

    public static HabitDTO fromDomain(Habit habit) {
        return new HabitDTO(habit.getHabitSeq(), habit.getTitle(), habit.getDuration(), habit.getDoDay(), habit.getTotalCount(), habit.getDoneCount(), habit.getState(), habit.getAlarmTime(), habit.getCreateDate());
    }

    public Habit toDomain() {
        return Habit.builder().title(title).duration(duration).doDay(doDay).totalCount(totalCount).doneCount(doneCount).state(state).alarmTime(alarmTime).createDate(createDate).build();
    }

}