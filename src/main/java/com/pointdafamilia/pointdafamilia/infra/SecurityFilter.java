package com.pointdafamilia.pointdafamilia.infra;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pointdafamilia.pointdafamilia.entities.User;
import com.pointdafamilia.pointdafamilia.repository.UserRepository;
import com.pointdafamilia.pointdafamilia.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        var token = this.recoverToken(request);
        if(token != null){
            var login = tokenService.validateToken(token);
            var username = tokenService.validateToken(token);

            UserDetails user = userRepository.findByLogin(login);
            UserDetails user2 = userRepository.findByUsername(username);

            var authentication = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
            var authentication2 = new UsernamePasswordAuthenticationToken(user2, null,user2.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication2);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }
        return token.replace("Bearer ", "");
    }
    
}
