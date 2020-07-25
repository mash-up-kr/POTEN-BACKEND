package com.mashup.poten.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mashup.poten.common.state.State;
import com.mashup.poten.domain.Habit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class HabitDTO {

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

    @JsonProperty("diarys")
    private List<DiaryDTO> diaryDTOList = new ArrayList<>();

    public static HabitDTO fromDomain(Habit habit) {
        if(habit.getDiarys() == null) {
            return new HabitDTO(habit.getHabitSeq(), habit.getTitle(), habit.getDuration(), habit.getDoDay(), habit.getTotalCount(), habit.getDoneCount(), habit.getLife(), habit.getState(), habit.getAlarmTime(), habit.getCreateDate(), null);
        }
        return new HabitDTO(habit.getHabitSeq(), habit.getTitle(), habit.getDuration(), habit.getDoDay(), habit.getTotalCount(), habit.getDoneCount(), habit.getLife(), habit.getState(), habit.getAlarmTime(), habit.getCreateDate(), habit.getDiarys().stream().map(DiaryDTO::fromDomain).collect(Collectors.toList()));
    }

    public Habit toDomain() {
        return Habit.builder().title(title).duration(duration).doDay(doDay).totalCount(totalCount).doneCount(doneCount).state(state).alarmTime(alarmTime).createDate(createDate).build();
    }

    public void removeDoDaySpace() {
        this.doDay = this.doDay.replaceAll(" ", "");
    }

}
