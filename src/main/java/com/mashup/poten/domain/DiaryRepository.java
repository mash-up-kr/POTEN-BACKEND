package com.mashup.poten.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {
    Diary findByHabit(Habit habit);

    List<Diary> findAllByHabit(Habit habit);
}
