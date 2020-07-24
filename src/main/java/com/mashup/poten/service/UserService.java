package com.mashup.poten.service;

import com.mashup.poten.common.response.ResponseMessage;
import com.mashup.poten.domain.User;
import com.mashup.poten.domain.UserRepository;
import com.mashup.poten.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserDTO signUp(UserDTO userDTO) throws Exception{
        if("" == userDTO.getUserId() || "" == userDTO.getUserPassword()) {
            throw new Exception(ResponseMessage.EMPTY_USER_ID_OR_PASSWORD);
        }
        User user = userRepository.save(userDTO.toDomain());
        return UserDTO.fromDomain(user);
    }
}
