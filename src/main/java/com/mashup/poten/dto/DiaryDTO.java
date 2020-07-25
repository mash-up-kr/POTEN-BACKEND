package com.mashup.poten.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mashup.poten.domain.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class DiaryDTO {

    private Integer diarySeq;

    private String content;

    private LocalDateTime createDate;

    public Diary toDomain() {
        return Diary.builder().content(content).createDate(createDate).build();
    }

    public static DiaryDTO fromDomain(Diary diary) {
        return new DiaryDTO(diary.getDiarySeq(), diary.getContent(), diary.getCreateDate());
    }

}
