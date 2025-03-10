package com.pointdafamilia.pointdafamilia.auth.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.pointdafamilia.pointdafamilia.auth.entity.UserDetailsImp;
import com.pointdafamilia.pointdafamilia.user.entity.User;
import com.pointdafamilia.pointdafamilia.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsImpService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String data) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(data);

        if(user == null){
            user = userRepository.findByUsername(data);
            if(user == null){
                throw new UsernameNotFoundException(data);
            }
        }
        return new UserDetailsImp(user);
    }


}
