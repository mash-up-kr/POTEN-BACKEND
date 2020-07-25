package com.mashup.poten.service;

import com.mashup.poten.common.jwt.JwtProvider;
import com.mashup.poten.common.response.ResponseMessage;
import com.mashup.poten.domain.User;
import com.mashup.poten.domain.UserRepository;
import com.mashup.poten.dto.UserDTO;
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

    public UserDTO signUp(UserDTO userDTO) throws Exception{
        if("".equals(userDTO.getSnsType()) || "".equals(userDTO.getToken())) {
            throw new Exception(ResponseMessage.INVALID_TOKEN);
        }
        User user = userRepository.save(userDTO.toDomain());
        return UserDTO.fromDomain(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userSeq) throws UsernameNotFoundException {
        User user = userRepository.getOne(Integer.valueOf(userSeq));
        if(!user.getUserSeq().equals(Integer.valueOf(userSeq))) {
            throw new UsernameNotFoundException("Invalid Request");
        }
        return org.springframework.security.core.userdetails.User.builder().username(userSeq).password("").roles("").build();
    }

    public UserDTO loginByToken(String token) throws Exception {
        if(jwtProvider.validateTokenIssuedDate(token)) {
            User user = userRepository.getOne(Integer.valueOf(jwtProvider.getUserSeq(token)));
            UserDTO userDTO = UserDTO.fromDomain(user);
            user.setSortedForToday(userDTO);
            return userDTO;
        }else {
            throw new Exception(ResponseMessage.INVALID_TOKEN);
        }
    }

    public UserDTO loginByOauth(UserDTO userDTO) throws Exception {
        User user = userRepository.findBySnsTypeAndToken(userDTO.getSnsType(), userDTO.getToken());
        if(user == null) {
            throw new Exception(ResponseMessage.NEED_TO_SIGN_UP);
        }
        String newToken = jwtProvider.createToken(String.valueOf(user.getUserSeq()));
        userDTO = UserDTO.fromDomain(user);
        userDTO.setToken(newToken);
        user.setSortedForToday(userDTO);
        return userDTO;
    }
}
