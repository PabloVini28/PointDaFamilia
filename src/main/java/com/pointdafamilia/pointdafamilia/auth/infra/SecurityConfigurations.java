package com.pointdafamilia.pointdafamilia.auth.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.pointdafamilia.pointdafamilia.auth.filter.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
        .requestMatchers(HttpMethod.POST, "/api/order/create-order").permitAll()
        .requestMatchers(HttpMethod.POST, "/api/food/create-food").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET, "/api/food/get-food-by-id").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT, "/api/food/update-food-by-id").hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/api/food/delete-food-by-id").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PATCH, "/api/food").hasRole("ADMIN")
        // .requestMatchers(HttpMethod.PATCH, "/api/food/{name}/description").hasRole("ADMIN")
        // .requestMatchers(HttpMethod.PATCH, "/api/food/{name}/ingredients").hasRole("ADMIN")
        // .requestMatchers(HttpMethod.PATCH, "/api/food/{name}/quantity").hasRole("ADMIN")
        // .requestMatchers(HttpMethod.PATCH, "/api/food/{name}/price").hasRole("ADMIN")
        // .requestMatchers(HttpMethod.PATCH, "/api/food/{name}/image").hasRole("ADMIN")
        // .requestMatchers(HttpMethod.PATCH, "/api/food/{name}/type").hasRole("ADMIN")
        .requestMatchers(HttpMethod.POST, "/api/drink/create-drink").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET, "/api/drink/get-drink-by-id").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT, "/api/drink/update-drink-by-id").hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/api/drink/delete-drink-by-id").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PATCH, "/api/drink").hasRole("ADMIN")
        .anyRequest().authenticated()
        )
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
