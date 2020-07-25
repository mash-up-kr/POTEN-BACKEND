package com.mashup.poten.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Integer> {

    List<Habit> findAllByUser(User user);

}
