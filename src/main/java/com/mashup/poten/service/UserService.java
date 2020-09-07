package com.mashup.poten.service;

import com.mashup.poten.common.jwt.JwtProvider;
import com.mashup.poten.common.response.ResponseMessage;
import com.mashup.poten.domain.User;
import com.mashup.poten.domain.UserRepository;
import com.mashup.poten.dto.user.request.SignInRequestDTO;
import com.mashup.poten.dto.user.request.SignUpRequestDTO;
import com.mashup.poten.dto.user.response.SignInResponseDTO;
import com.mashup.poten.dto.user.response.SignUpResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO) throws Exception{
        if("".equals(signUpRequestDTO.getSnsType()) || "".equals(signUpRequestDTO.getToken())) {
            throw new Exception(ResponseMessage.INVALID_TOKEN);
        }

        if(userRepository.findByNickname(signUpRequestDTO.getNickname()) != null) {
            throw new Exception(ResponseMessage.DUPLICATE_NICKNAME);
        }
        User user = userRepository.save(signUpRequestDTO.toDomain());
        return new SignUpResponseDTO(user.getUserSeq(), user.getSnsType(), user.getToken(), user.getNickname());
    }

    @Override
    public UserDetails loadUserByUsername(String userSeq) throws UsernameNotFoundException {
        User user = userRepository.getOne(Integer.valueOf(userSeq));
        if(!user.getUserSeq().equals(Integer.valueOf(userSeq))) {
            throw new UsernameNotFoundException("Invalid Request");
        }
        return org.springframework.security.core.userdetails.User.builder().username(userSeq).password("").roles("").build();
    }

    public SignInResponseDTO loginByToken(String token) throws Exception {
        if(jwtProvider.validateTokenIssuedDate(token)) {
            User user = userRepository.getOne(Integer.valueOf(jwtProvider.getUserSeq(token)));
            return new SignInResponseDTO(user.getUserSeq(), user.getToken(), user.getNickname());
        }else {
            throw new Exception(ResponseMessage.INVALID_TOKEN);
        }
    }

    public SignInResponseDTO loginByOauth(SignInRequestDTO signInRequestDTO) throws Exception {
        User user = userRepository.findBySnsTypeAndToken(signInRequestDTO.getSnsType(), signInRequestDTO.getToken());
        if(user == null) {
            throw new Exception(ResponseMessage.NEED_TO_SIGN_UP);
        }

        String token = jwtProvider.createToken(String.valueOf(user.getUserSeq()));

        return new SignInResponseDTO(user.getUserSeq(), token, user.getNickname());
    }
}
