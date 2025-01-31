package com.pointdafamilia.pointdafamilia.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pointdafamilia.pointdafamilia.dtos.AuthenticationDto;
import com.pointdafamilia.pointdafamilia.dtos.LoginResponseDto;
import com.pointdafamilia.pointdafamilia.dtos.RegisterDto;
import com.pointdafamilia.pointdafamilia.entities.User;
import com.pointdafamilia.pointdafamilia.enums.Role;
import com.pointdafamilia.pointdafamilia.repository.UserRepository;
import com.pointdafamilia.pointdafamilia.services.TokenService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    
    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(),data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);   

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto data) {
        if(this.userRepository.findByLogin(data.login())!= null){
            return ResponseEntity.badRequest().build();
        }
        else{
            String encryptedPassWord = new BCryptPasswordEncoder().encode(data.password());
            User newUser = new User(data.login(),data.username(),encryptedPassWord,Role.ROLE_USER);
            this.userRepository.save(newUser);
            return ResponseEntity.ok().build();
        }
        
    }
    
    

}
