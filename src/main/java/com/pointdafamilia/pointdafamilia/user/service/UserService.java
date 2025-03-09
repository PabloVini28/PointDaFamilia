package com.pointdafamilia.pointdafamilia.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pointdafamilia.pointdafamilia.user.dtos.UserDto;
import com.pointdafamilia.pointdafamilia.user.exceptions.UserNameNotFoundException;
import com.pointdafamilia.pointdafamilia.user.exceptions.UserEmailNotFoundException;
import com.pointdafamilia.pointdafamilia.user.exceptions.UserIdNotFoundException;
import com.pointdafamilia.pointdafamilia.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public UserDto getById(Long id) throws Exception{
        if(!userRepository.existsById(id)){
            throw new UserIdNotFoundException(id);
        }
        UserDto user = userRepository.findItById(id);
        return user;
    }

    public UserDto getByUsername(String username) throws Exception{
        if(!userRepository.existsByUsername(username)){
            throw new UserNameNotFoundException(username);
        }
        UserDto user = userRepository.findByUsername(username);
        return user;
    }

    public UserDto getByEmail(String email) throws Exception{
        if(!userRepository.existsByEmail(email)){
            throw new UserEmailNotFoundException(email);
        }
        UserDto user = userRepository.findByEmail();
        return user;
    }

}
