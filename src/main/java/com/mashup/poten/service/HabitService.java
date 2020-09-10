package com.mashup.poten.service;

import com.mashup.poten.common.jwt.JwtProvider;
import com.mashup.poten.common.response.ResponseMessage;
import com.mashup.poten.domain.Habit;
import com.mashup.poten.domain.HabitRepository;
import com.mashup.poten.domain.User;
import com.mashup.poten.domain.UserRepository;
import com.mashup.poten.dto.habit.request.AddHabitRequestDTO;
import com.mashup.poten.dto.habit.response.AddHabitResponseDTO;
import com.mashup.poten.dto.habit.response.HabitResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HabitService {

    private final JwtProvider jwtProvider;
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;


    public AddHabitResponseDTO addHabit(HttpServletRequest request, AddHabitRequestDTO addHabitRequestDTO) throws Exception{
        String token = request.getHeader(JwtProvider.HEADER_NAME);
        addHabitRequestDTO.removeDoDaySpace();
        User user = userRepository.findById(Integer.valueOf(jwtProvider.getUserSeq(token))).orElseThrow(() ->  new Exception(ResponseMessage.INVALID_TOKEN));
        Habit habit = addHabitRequestDTO.toDomain();
        habit.setOwner(user);
        habit.setState();
        habit.setCreateDate();
        habit.setTotalCount();
        habit.setLife();
        habit = habitRepository.save(habit);
        return new AddHabitResponseDTO(habit.getHabitSeq(), habit.getTitle(), habit.getDuration(), habit.getDoDay(), habit.getTotalCount(), habit.getDoneCount(), habit.getLife(), habit.getCurrentLife(), habit.getState(), habit.getAlarmTime(), habit.getCreateDate(), habit.getCharacterCode());
    }

    private Habit getHabitWitchCheckHabitOwner(String userSeq, Integer habitSeq) throws Exception{
        User user = userRepository.findById(Integer.valueOf(userSeq)).orElseThrow(() ->  new Exception(ResponseMessage.INVALID_TOKEN));
        Habit habit = habitRepository.findById(habitSeq).orElseThrow(() ->  new Exception(ResponseMessage.INVALID_TOKEN));
        if(user.getUserSeq().intValue() != habit.getUser().getUserSeq().intValue()) {
            throw new Exception(ResponseMessage.INVALID_TOKEN);
        }
        return habit;
    }

    public List<HabitResponseDTO> getHabits(HttpServletRequest request) throws Exception{
        String token = request.getHeader(JwtProvider.HEADER_NAME);
        Integer userSeq = Integer.valueOf(jwtProvider.getUserSeq(token));
        User user = userRepository.findById(userSeq).orElseThrow(() ->  new Exception(ResponseMessage.INVALID_TOKEN));
        List<Habit> habits = habitRepository.findAllByUser(user);
        LocalDate date = LocalDate.now();
        System.out.println(date.getDayOfWeek().toString());
        List<Habit> todayhabits = habits.stream().filter(habit -> habit.getDoDay().contains(date.getDayOfWeek().toString())).sorted().collect(Collectors.toList());
        List<Habit> notTodayhabits = habits.stream().filter(habit -> !habit.getDoDay().contains(date.getDayOfWeek().toString())).sorted().collect(Collectors.toList());

        todayhabits.addAll(notTodayhabits);
        doSortingForToday(habits);


        return todayhabits.stream().map(HabitResponseDTO::fromDomain).collect(Collectors.toList());
    }

    private void doSortingForToday(List<Habit> habits) {
        LocalDate date = LocalDate.now();

        List<Habit> todayhabits = habits.stream().filter(habit -> habit.getDoDay().contains(date.getDayOfWeek().toString())).sorted().collect(Collectors.toList());
        List<Habit> notTodayhabits = habits.stream().filter(habit -> !habit.getDoDay().contains(date.getDayOfWeek().toString())).sorted().collect(Collectors.toList());

        todayhabits.addAll(notTodayhabits);

        habits = todayhabits;
    }

    public HabitResponseDTO getHabit(HttpServletRequest request, Integer habitSeq) throws Exception{
        String token = request.getHeader(JwtProvider.HEADER_NAME);
        return HabitResponseDTO.fromDomain(getHabitWitchCheckHabitOwner(jwtProvider.getUserSeq(token), habitSeq));
    }
}
