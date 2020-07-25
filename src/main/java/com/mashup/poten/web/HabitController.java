package com.mashup.poten.web;

import com.mashup.poten.common.response.Response;
import com.mashup.poten.common.response.ResponseCode;
import com.mashup.poten.dto.HabitDTO;
import com.mashup.poten.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/habit")
@RequiredArgsConstructor
@RestController
public class HabitController {

    private final HabitService habitService;

    @PostMapping("")
    public Response addHabit(HttpServletRequest request, @RequestBody HabitDTO habitDTO) {
        try {
            return Response.builder().responseCode(ResponseCode.SUCCESS).responseData(habitService.addHabit(request, habitDTO)).build();
        } catch (Exception e) {
            return Response.builder().responseCode(ResponseCode.FAIL).responseData(e.getMessage()).build();
        }
    }

    @GetMapping("/list")
    public Response getHabits(HttpServletRequest request) {
        try {
            return Response.builder().responseCode(ResponseCode.SUCCESS).responseData(habitService.getHabits(request)).build();
        } catch (Exception e) {
            return Response.builder().responseCode(ResponseCode.FAIL).responseData(e.getMessage()).build();
        }
    }

    @GetMapping("/{habitSeq}")
    public Response getHabit(HttpServletRequest request, @PathVariable("habitSeq") Integer habitSeq) {
        try {
            return Response.builder().responseCode(ResponseCode.SUCCESS).responseData(habitService.getHabit(request, habitSeq)).build();
        } catch (Exception e) {
            return Response.builder().responseCode(ResponseCode.FAIL).responseData(e.getMessage()).build();
        }
    }
}
