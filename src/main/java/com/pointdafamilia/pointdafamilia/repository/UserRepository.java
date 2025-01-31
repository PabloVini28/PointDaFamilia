package com.pointdafamilia.pointdafamilia.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.pointdafamilia.pointdafamilia.entities.User;
import com.pointdafamilia.pointdafamilia.enums.Role;

public interface UserRepository extends JpaRepository<User,Long>{
    UserDetails findByLoginOrEmail(String login, String email);

    boolean existsByRole(Role roleAdmin);
} 
