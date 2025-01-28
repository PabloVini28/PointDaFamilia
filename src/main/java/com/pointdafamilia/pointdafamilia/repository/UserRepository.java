package com.pointdafamilia.pointdafamilia.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.pointdafamilia.pointdafamilia.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{
    UserDetails findByLogin(String login);
} 
