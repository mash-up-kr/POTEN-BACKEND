package com.mashup.poten.web;

import com.mashup.poten.common.response.Response;
import com.mashup.poten.common.response.ResponseCode;
import com.mashup.poten.dto.AssignmentDTO;
import com.mashup.poten.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/assignment")
@RequiredArgsConstructor
@RestController
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping("")
    public Response addAssignment(HttpServletRequest request, @RequestBody AssignmentDTO assignmentDTO) {
        try {
            return Response.builder().responseCode(ResponseCode.SUCCESS).responseData(assignmentService.addAssignment(request, assignmentDTO)).build();
        } catch (Exception e) {
            return Response.builder().responseCode(ResponseCode.SUCCESS).responseData(e.getMessage()).build();
        }
    }

    @GetMapping("/list")
    public Response getAssignments(HttpServletRequest request) {
        try {
            return Response.builder().responseCode(ResponseCode.SUCCESS).responseData(assignmentService.getAssignments(request)).build();
        } catch (Exception e) {
            return Response.builder().responseCode(ResponseCode.SUCCESS).responseData(e.getMessage()).build();
        }
    }

    @GetMapping("/{assignmentSeq}")
    public Response getAssignment(HttpServletRequest request, @PathVariable("assignmentSeq") Integer assignmentSeq) {
        try {
            return Response.builder().responseCode(ResponseCode.SUCCESS).responseData(assignmentService.getAssignment(request, assignmentSeq)).build();
        } catch (Exception e) {
            return Response.builder().responseCode(ResponseCode.SUCCESS).responseData(e.getMessage()).build();
        }
    }
}
