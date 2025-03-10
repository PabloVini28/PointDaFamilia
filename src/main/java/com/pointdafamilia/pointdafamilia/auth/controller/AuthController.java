package com.pointdafamilia.pointdafamilia.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pointdafamilia.pointdafamilia.auth.dtos.request.LoginDto;
import com.pointdafamilia.pointdafamilia.auth.dtos.request.RegisterDto;
import com.pointdafamilia.pointdafamilia.user.entity.User;
import com.pointdafamilia.pointdafamilia.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody @Valid LoginDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var  auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterDto data) {
        if(this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().body("Email already registered!");
        if(this.userRepository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().body("Username already registered!");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data,encryptedPassword);
        user = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    

    
}
