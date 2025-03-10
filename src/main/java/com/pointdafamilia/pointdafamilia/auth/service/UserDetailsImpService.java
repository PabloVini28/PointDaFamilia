package com.pointdafamilia.pointdafamilia.auth.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.pointdafamilia.pointdafamilia.auth.entity.UserDetailsImp;
import com.pointdafamilia.pointdafamilia.auth.exceptions.UserEmailOrUsernameNotFoundException;
import com.pointdafamilia.pointdafamilia.user.entity.User;
import com.pointdafamilia.pointdafamilia.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsImpService {
    
    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsernameOrEmail(String data) throws Exception{
        User user = userRepository.findByEmail(data);

        if(user == null){
            user = userRepository.findByUsername(data);
            if(user == null){
                throw new UserEmailOrUsernameNotFoundException(data);
            }
        }
        return new UserDetailsImp(user);
    }


}
