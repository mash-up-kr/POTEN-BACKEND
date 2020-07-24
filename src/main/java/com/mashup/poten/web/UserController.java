package com.mashup.poten.web;

import com.mashup.poten.common.response.Response;
import com.mashup.poten.common.response.ResponseCode;
import com.mashup.poten.dto.UserDTO;
import com.mashup.poten.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public Response login(@RequestParam UserDTO userDTO) {
        try{
            return Response.builder().responseCode(ResponseCode.SUCCESS).responseData("success").build();
        }catch (Exception e) {
            return Response.builder().responseCode(ResponseCode.FAIL).responseData(e.toString()).build();
        }
    }

    @PostMapping("/sign-up")
    public Response signUp(@RequestParam UserDTO userDTO) {
        try{
            return Response.builder().responseCode(ResponseCode.SUCCESS).responseData(userService.signUp(userDTO)).build();
        }catch (Exception e) {
            return Response.builder().responseCode(ResponseCode.FAIL).responseData(e.toString()).build();
        }
    }
}
