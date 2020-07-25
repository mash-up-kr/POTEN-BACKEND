package com.mashup.poten.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private String state;

    private int alarmTime;

    private LocalDateTime createDate;

    @JoinColumn(name = "userSeq")
    @ManyToOne
    private User user;

    @Builder
    public Habit(Integer habitSeq, String title, int duration, String doDay, int totalCount, int doneCount, String state, int alarmTime, LocalDateTime createDate) {
        this.habitSeq = habitSeq;
        this.title = title;
        this.duration = duration;
        this.doDay = doDay;
        this.totalCount = totalCount;
        this.doneCount = doneCount;
        this.state = state;
        this.alarmTime = alarmTime;
        this.createDate =createDate;
    }


    @Override
    public int compareTo(Habit habit) {
        int remainDayCount = this.totalCount - this.doneCount;
        int nexthabitRemainDayCount = habit.getTotalCount() - habit.getDoneCount();
        return (nexthabitRemainDayCount - remainDayCount);
    }

    @PrePersist
    public void setCreateDate() {
        this.createDate = LocalDateTime.now();
    }

    public void setOwner(User user) {
        this.user = user;
    }

}
