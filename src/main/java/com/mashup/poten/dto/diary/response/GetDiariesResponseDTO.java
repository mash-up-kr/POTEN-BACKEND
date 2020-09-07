package com.mashup.poten.dto.diary.response;

import com.mashup.poten.domain.Diary;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetDiariesResponseDTO {
    private Integer diarySeq;

    private String content;

    private LocalDateTime createDate;

    public static GetDiariesResponseDTO fromDomain(Diary diary) {
        return new GetDiariesResponseDTO(diary.getDiarySeq(), diary.getContent(), diary.getCreateDate());
    }
}
