package com.mashup.poten.service;

import com.mashup.poten.common.jwt.JwtProvider;
import com.mashup.poten.common.response.ResponseMessage;
import com.mashup.poten.domain.Diary;
import com.mashup.poten.domain.DiaryRepository;
import com.mashup.poten.domain.Habit;
import com.mashup.poten.domain.HabitRepository;
import com.mashup.poten.dto.diary.request.AddDiaryRequestDTO;
import com.mashup.poten.dto.diary.response.AddDiaryResponseDTO;
import com.mashup.poten.dto.diary.response.GetDiariesResponseDTO;
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

    public List<AddDiaryResponseDTO> addDiary(HttpServletRequest request, Integer habitSeq, AddDiaryRequestDTO addDiaryRequestDTO) throws Exception{
        String token = request.getHeader(JwtProvider.HEADER_NAME);
        Habit habit = getHabitWithCheckOwner(token, habitSeq);
        Diary diary = addDiaryRequestDTO.toDomain();
        diary.setHabit(habit);
        diaryRepository.save(diary);
        return diaryRepository.findAllByHabit(habit).stream().map(AddDiaryResponseDTO::fromDomain).collect(Collectors.toList());
    }

    public Habit getHabitWithCheckOwner(String token, Integer habitSeq) throws Exception{
        Integer userSeq = Integer.valueOf(jwtProvider.getUserSeq(token));
        Habit habit = habitRepository.findById(habitSeq).orElseThrow(() -> new Exception(ResponseMessage.INVALID_TOKEN));
        if(userSeq.intValue() != habit.getUser().getUserSeq().intValue()) {
            throw new Exception(ResponseMessage.INVALID_TOKEN);
        }
        return habit;

    }

    public List<GetDiariesResponseDTO> getDiaries(HttpServletRequest request, Integer habitSeq) throws Exception{
        String token = request.getHeader(JwtProvider.HEADER_NAME);
        Habit habit = getHabitWithCheckOwner(token, habitSeq);
        return diaryRepository.findAllByHabit(habit).stream().map(GetDiariesResponseDTO::fromDomain).collect(Collectors.toList());
    }
}
