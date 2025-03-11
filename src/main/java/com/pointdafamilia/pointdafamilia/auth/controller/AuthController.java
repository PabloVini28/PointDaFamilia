package com.pointdafamilia.pointdafamilia.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pointdafamilia.pointdafamilia.auth.dtos.request.LoginDto;
import com.pointdafamilia.pointdafamilia.auth.dtos.request.RegisterDto;
import com.pointdafamilia.pointdafamilia.auth.dtos.response.LoginResponseDto;
import com.pointdafamilia.pointdafamilia.auth.entity.UserDetailsImp;
import com.pointdafamilia.pointdafamilia.auth.service.JWTService;
import com.pointdafamilia.pointdafamilia.user.entity.User;
import com.pointdafamilia.pointdafamilia.user.enums.RoleType;
import com.pointdafamilia.pointdafamilia.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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

    @Autowired
    private JWTService jwtService;
    
    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody @Valid LoginDto data) {
        try{
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var  auth = this.authenticationManager.authenticate(usernamePassword);

            UserDetailsImp userDetails = (UserDetailsImp) auth.getPrincipal();
            User user = userDetails.getUser(); 

            var token = jwtService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDto(token));
        } catch (AuthenticationException e) {
            System.out.println("Erro de autenticação: " + e.getMessage()); // Log para depuração
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário inexistente ou senha inválida");
        }
    
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterDto data) {
        if(this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().body("Email already registered!");
        if(this.userRepository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().body("Username already registered!");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data,encryptedPassword,RoleType.ROLE_ADMIN);
        user = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    

    
}
