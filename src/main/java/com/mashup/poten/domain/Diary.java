package com.mashup.poten.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Diary {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer diarySeq;

    private String content;

    private LocalDateTime createDate;

    @JoinColumn(name = "habit_seq")
    @ManyToOne
    private Habit habit;

    @Builder
    public Diary(Integer diarySeq, String content, LocalDateTime createDate, Habit habit) {
        this.diarySeq = diarySeq;
        this.content = content;
        this.createDate = createDate;
        this.habit = habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    @PrePersist
    public void setCreateDate() {
        this.createDate = LocalDateTime.now();
    }
}
