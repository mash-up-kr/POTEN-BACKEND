package com.mashup.poten.web;

import com.mashup.poten.common.jwt.JwtProvider;
import com.mashup.poten.common.response.Response;
import com.mashup.poten.common.response.ResponseCode;
import com.mashup.poten.common.response.ResponseMessage;
import com.mashup.poten.dto.user.request.SignInRequestDTO;
import com.mashup.poten.dto.user.request.SignUpRequestDTO;
import com.mashup.poten.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-in")
    public Response login(HttpServletRequest request, @RequestBody SignInRequestDTO signInRequestDTO) {
        try{
            String token = request.getHeader(JwtProvider.HEADER_NAME);
            if(token != null) {
                return Response.builder().responseCode(ResponseCode.SUCCESS).responseData(userService.loginByToken(token)).build();
            }else {
                return Response.builder().responseCode(ResponseCode.SUCCESS).responseData(userService.loginByOauth(signInRequestDTO)).build();
            }
        }catch (Exception e) {
            if(e.getMessage().equals(ResponseMessage.NEED_TO_SIGN_UP)) {
                return Response.builder().responseCode(ResponseCode.NEED_TO_SIGN_UP).responseData(e.getMessage()).build();
            }
            return Response.builder().responseCode(ResponseCode.FAIL).responseData(e.getMessage()).build();
        }
    }

    @PostMapping("/sign-up")
    public Response signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        try{
            return Response.builder().responseCode(ResponseCode.SUCCESS).responseData(userService.signUp(signUpRequestDTO)).build();
        }catch (Exception e) {
            if(e.getMessage().equals(ResponseMessage.DUPLICATE_NICKNAME)) {
                return Response.builder().responseCode(ResponseCode.DUPLICATE_NICKNAME).responseData(e.getMessage()).build();
            }
            return Response.builder().responseCode(ResponseCode.FAIL).responseData(e.toString()).build();
        }
    }
}
