package com.pointdafamilia.pointdafamilia.auth.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
        .requestMatchers(HttpMethod.POST, "/api/food/create-food").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET, "/api/food/get-food-by-id").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT, "/api/food/update-food-by-id").hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/api/food/delete-food-by-id").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PATCH, "/api/food/{name}/name").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PATCH, "/api/food/{name}/description").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PATCH, "/api/food/{name}/ingredients").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PATCH, "/api/food/{name}/quantity").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PATCH, "/api/food/{name}/price").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PATCH, "/api/food/{name}/image").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PATCH, "/api/food/{name}/type").hasRole("ADMIN")
        .anyRequest().authenticated()
        )
        .build();
    }

}
