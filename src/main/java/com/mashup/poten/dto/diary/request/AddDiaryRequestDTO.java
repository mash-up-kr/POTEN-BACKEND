package com.mashup.poten.dto.diary.request;

import com.mashup.poten.domain.Diary;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddDiaryRequestDTO {

    private String content;

    private LocalDateTime createDate;

    public Diary toDomain() {
        return Diary.builder().content(content).createDate(createDate).build();
    }

}
