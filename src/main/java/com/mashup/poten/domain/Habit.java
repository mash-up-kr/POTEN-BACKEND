package com.mashup.poten.domain;

import com.mashup.poten.common.state.State;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * habit Domain 객체
 * 과제에 대한 비즈니스 로직을 담당
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Habit implements Comparable<Habit> {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer habitSeq;

    private String title;

    private int duration;

    private String doDay;

    private int totalCount;

    private int doneCount;

    private int life;

    private int currentLife;

    @Enumerated(EnumType.STRING)
    private State state;

    private int alarmTime;

    private LocalDateTime createDate;

    private int characterCode;

    @JoinColumn(name = "user_seq")
    @ManyToOne
    private User user;

    // @TODO 과일 코드

    // @TODO currentCount : 현재날 제외 하고 count

    // @TODO currentCount - doneCount >= life => state : FAIL

    @Builder
    public Habit(Integer habitSeq, String title, int duration, String doDay, int totalCount, int doneCount, int life, int currentLife, State state, int alarmTime, LocalDateTime createDate, int characterCode) {
        this.habitSeq = habitSeq;
        this.title = title;
        this.duration = duration;
        this.doDay = doDay;
        this.totalCount = totalCount;
        this.doneCount = doneCount;
        this.life = life;
        this.currentLife = currentLife;
        this.state = state;
        this.alarmTime = alarmTime;
        this.createDate =createDate;
        this.characterCode = characterCode;
    }


    @Override
    public int compareTo(Habit habit) {
        int remainDayCount = this.totalCount - this.doneCount;
        int nexthabitRemainDayCount = habit.getTotalCount() - habit.getDoneCount();
        return (remainDayCount - nexthabitRemainDayCount);
    }

    @PrePersist
    public void setCreateDate() {
        this.createDate = LocalDateTime.now();
    }

    public void setOwner(User user) {
        this.user = user;
    }

    public void setTotalCount() {
        int cnt = 0;
        String[] dows = this.doDay.split(";");
        LocalDate currDate = LocalDate.now();
        String day = currDate.getDayOfWeek().toString();

        for(int i = 0; i < this.duration; i++) {
            switch(day) {
                case "MONDAY":
                    if(Arrays.stream(dows).anyMatch(day::equals)) cnt++;
                    break;
                case "TUESDAY":
                    if(Arrays.stream(dows).anyMatch(day::equals)) cnt++;
                    break;
                case "WEDNESDAY":
                    if(Arrays.stream(dows).anyMatch(day::equals)) cnt++;
                    break;
                case "THURSDAY":
                    if(Arrays.stream(dows).anyMatch(day::equals)) cnt++;
                    break;
                case "FRIDAY":
                    if(Arrays.stream(dows).anyMatch(day::equals)) cnt++;
                    break;
                case "SATURDAY":
                    if(Arrays.stream(dows).anyMatch(day::equals)) cnt++;
                    break;
                case "SUNDAY":
                    if(Arrays.stream(dows).anyMatch(day::equals)) cnt++;
                    break;
            }
            currDate = currDate.plusDays(1);
            day = currDate.getDayOfWeek().toString();
        }
        this.totalCount = cnt;
    }

    public void updateStatus() {

    }

    public void setLife() {
        int totalLife = (this.totalCount / 10) + 1;
        this.life = (totalLife > 5 ? 5 : totalLife);
        this.currentLife = this.life;
    }

    public void setState() {
        this.state = State.ING;
    }

}
