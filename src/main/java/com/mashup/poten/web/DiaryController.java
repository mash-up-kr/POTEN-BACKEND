package com.mashup.poten.web;

import com.mashup.poten.common.response.Response;
import com.mashup.poten.common.response.ResponseCode;
import com.mashup.poten.dto.DiaryDTO;
import com.mashup.poten.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/diary")
@RequiredArgsConstructor
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/{habitSeq}")
    public Response addDiary(HttpServletRequest request, @PathVariable("habitSeq") Integer habitSeq, @RequestBody DiaryDTO diaryDTO) {
        try {
            return Response.builder().responseCode(ResponseCode.SUCCESS).responseData(diaryService.addDiary(request, habitSeq, diaryDTO)).build();
        }catch(Exception e) {
            return Response.builder().responseCode(ResponseCode.FAIL).responseData(e.getMessage()).build();
        }
    }

}
