package com.mashup.poten.service;

import com.mashup.poten.common.jwt.JwtProvider;
import com.mashup.poten.common.response.ResponseMessage;
import com.mashup.poten.domain.Diary;
import com.mashup.poten.domain.DiaryRepository;
import com.mashup.poten.domain.Habit;
import com.mashup.poten.domain.HabitRepository;
import com.mashup.poten.dto.DiaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final JwtProvider jwtProvider;
    private final DiaryRepository diaryRepository;
    private final HabitRepository habitRepository;

    public List<DiaryDTO> addDiary(HttpServletRequest request, Integer habitSeq, DiaryDTO diaryDTO) throws Exception{
        String token = request.getHeader(JwtProvider.HEADER_NAME);
        Habit habit = getHabitWithCheckOwner(token, habitSeq);
        Diary diary = diaryDTO.toDomain();
        diary.setHabit(habit);
        diaryRepository.save(diary);
        return diaryRepository.findAllByHabit(habit).stream().map(DiaryDTO::fromDomain).collect(Collectors.toList());
    }

    public Habit getHabitWithCheckOwner(String token, Integer habitSeq) throws Exception{
        Integer userSeq = Integer.valueOf(jwtProvider.getUserSeq(token));
        Habit habit = habitRepository.findById(habitSeq).orElseThrow(() -> new Exception(ResponseMessage.INVALID_TOKEN));
        if(userSeq.intValue() != habit.getUser().getUserSeq().intValue()) {
            throw new Exception(ResponseMessage.INVALID_TOKEN);
        }
        return habit;

    }
}
