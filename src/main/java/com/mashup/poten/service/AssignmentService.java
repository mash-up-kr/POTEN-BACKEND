package com.mashup.poten.service;

import com.mashup.poten.common.jwt.JwtProvider;
import com.mashup.poten.common.response.ResponseMessage;
import com.mashup.poten.domain.Assignment;
import com.mashup.poten.domain.AssignmentRepository;
import com.mashup.poten.domain.User;
import com.mashup.poten.domain.UserRepository;
import com.mashup.poten.dto.AssignmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AssignmentService {

    private final JwtProvider jwtProvider;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;


    public AssignmentDTO addAssignment(HttpServletRequest request, AssignmentDTO assignmentDTO) throws Exception{
        String token = request.getHeader(JwtProvider.HEADER_NAME);
        User user = userRepository.findById(Integer.valueOf(jwtProvider.getUserSeq(token))).orElseThrow(() ->  new Exception(ResponseMessage.INVALID_TOKEN));
        Assignment assignment = assignmentDTO.toDomain();
        assignment.setOwner(user);
        return AssignmentDTO.fromDomain(assignmentRepository.save(assignment));
    }

    private Assignment getAssignmentWitchCheckAssignmentOwner(String userSeq, Integer assignmentSeq) throws Exception{
        User user = userRepository.findById(Integer.valueOf(userSeq)).orElseThrow(() ->  new Exception(ResponseMessage.INVALID_TOKEN));
        Assignment assignment = assignmentRepository.findById(assignmentSeq).orElseThrow(() ->  new Exception(ResponseMessage.INVALID_TOKEN));
        if(user.getUserSeq().intValue() != assignment.getUser().getUserSeq().intValue()) {
            throw new Exception(ResponseMessage.INVALID_TOKEN);
        }
        return assignment;
    }

    public List<AssignmentDTO> getAssignments(HttpServletRequest request) throws Exception{
        String token = request.getHeader(JwtProvider.HEADER_NAME);
        Integer userSeq = Integer.valueOf(jwtProvider.getUserSeq(token));
        User user = userRepository.findById(userSeq).orElseThrow(() ->  new Exception(ResponseMessage.INVALID_TOKEN));
        List<Assignment> assignments = assignmentRepository.findAllByUser(user);
        LocalDate date = LocalDate.now();
        System.out.println(date.getDayOfWeek().toString());
        List<Assignment> todayAssignments = assignments.stream().filter(assignment -> assignment.getDoDay().contains(date.getDayOfWeek().toString())).sorted().collect(Collectors.toList());
        List<Assignment> notTodayAssignments = assignments.stream().filter(assignment -> !assignment.getDoDay().contains(date.getDayOfWeek().toString())).sorted().collect(Collectors.toList());

        todayAssignments.addAll(notTodayAssignments);

        return todayAssignments.stream().map(AssignmentDTO::fromDomain).collect(Collectors.toList());
    }

    public AssignmentDTO getAssignment(HttpServletRequest request, Integer assignmentSeq) throws Exception{
        String token = request.getHeader(JwtProvider.HEADER_NAME);
        return AssignmentDTO.fromDomain(getAssignmentWitchCheckAssignmentOwner(jwtProvider.getUserSeq(token), assignmentSeq));
    }
}
